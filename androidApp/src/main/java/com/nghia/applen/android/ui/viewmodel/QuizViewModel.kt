package com.nghia.applen.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nghia.applen.data.repository.QuizRepository
import com.nghia.applen.model.Quiz
import com.nghia.applen.model.Question
import com.nghia.applen.model.QuizAttempt
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class QuizUiState(
    val quizzes: List<Quiz> = emptyList(),
    val currentQuiz: Quiz? = null,
    val currentQuestion: Question? = null,
    val currentQuestionIndex: Int = 0,
    val userAnswers: Map<String, String> = emptyMap(), // questionId to answer
    val timeRemainingSeconds: Int = 0,
    val attemptId: String? = null,
    val isQuizActive: Boolean = false,
    val showResults: Boolean = false,
    val score: Int = 0,
    val correctCount: Int = 0,
    val isLoading: Boolean = false,
    val error: String? = null
)

class QuizViewModel(
    private val repository: QuizRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()
    
    private var timerJob: Job? = null
    private var quizStartTime: Long = 0
    
    init {
        loadQuizzes()
    }
    
    private fun loadQuizzes() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            repository.getAllQuizzes()
                .catch { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Unknown error"
                        )
                    }
                }
                .collect { quizzes ->
                    _uiState.update {
                        it.copy(
                            quizzes = quizzes,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }
    
    /**
     * Start a quiz
     */
    fun startQuiz(quizId: String) {
        viewModelScope.launch {
            val quiz = repository.getQuizById(quizId) ?: return@launch
            
            // Create attempt
            val attemptId = repository.startAttempt(quiz.id, quiz.questions.size)
            
            quizStartTime = System.currentTimeMillis()
            
            _uiState.update {
                it.copy(
                    currentQuiz = quiz,
                    currentQuestion = quiz.questions.firstOrNull(),
                    currentQuestionIndex = 0,
                    userAnswers = emptyMap(),
                    timeRemainingSeconds = quiz.duration * 60,
                    attemptId = attemptId,
                    isQuizActive = true,
                    showResults = false,
                    score = 0,
                    correctCount = 0
                )
            }
            
            // Start timer
            startTimer()
        }
    }
    
    /**
     * Start countdown timer
     */
    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.timeRemainingSeconds > 0 && _uiState.value.isQuizActive) {
                delay(1000)
                _uiState.update {
                    it.copy(timeRemainingSeconds = it.timeRemainingSeconds - 1)
                }
            }
            
            // Time's up
            if (_uiState.value.isQuizActive) {
                submitQuiz()
            }
        }
    }
    
    /**
     * Submit answer for current question
     */
    fun submitAnswer(answer: String) {
        val currentQuestion = _uiState.value.currentQuestion ?: return
        val attemptId = _uiState.value.attemptId ?: return
        
        viewModelScope.launch {
            // Save answer
            repository.submitAnswer(
                attemptId = attemptId,
                questionId = currentQuestion.id,
                userAnswer = answer,
                correctAnswer = currentQuestion.correctAnswer
            )
            
            // Update local state
            _uiState.update {
                it.copy(
                    userAnswers = it.userAnswers + (currentQuestion.id to answer)
                )
            }
        }
    }
    
    /**
     * Move to next question
     */
    fun nextQuestion() {
        val currentQuiz = _uiState.value.currentQuiz ?: return
        val nextIndex = _uiState.value.currentQuestionIndex + 1
        
        if (nextIndex < currentQuiz.questions.size) {
            _uiState.update {
                it.copy(
                    currentQuestion = currentQuiz.questions[nextIndex],
                    currentQuestionIndex = nextIndex
                )
            }
        } else {
            // Quiz completed
            submitQuiz()
        }
    }
    
    /**
     * Move to previous question
     */
    fun previousQuestion() {
        val currentQuiz = _uiState.value.currentQuiz ?: return
        val prevIndex = _uiState.value.currentQuestionIndex - 1
        
        if (prevIndex >= 0) {
            _uiState.update {
                it.copy(
                    currentQuestion = currentQuiz.questions[prevIndex],
                    currentQuestionIndex = prevIndex
                )
            }
        }
    }
    
    /**
     * Submit quiz and calculate results
     */
    fun submitQuiz() {
        timerJob?.cancel()
        
        val currentQuiz = _uiState.value.currentQuiz ?: return
        val attemptId = _uiState.value.attemptId ?: return
        val userAnswers = _uiState.value.userAnswers
        
        // Calculate score
        var correctCount = 0
        var totalPoints = 0
        
        currentQuiz.questions.forEach { question ->
            val userAnswer = userAnswers[question.id] ?: ""
            if (userAnswer.equals(question.correctAnswer)) {
                correctCount++
                totalPoints += question.points
            }
        }
        
        val maxPoints = currentQuiz.questions.sumOf { it.points }
        val scorePercentage = if (maxPoints > 0) {
            (totalPoints * 100) / maxPoints
        } else {
            0
        }
        
        // Calculate time spent
        val timeSpentMs = System.currentTimeMillis() - quizStartTime
        val timeSpentSeconds = (timeSpentMs / 1000).toInt()
        
        viewModelScope.launch {
            // Save attempt
            repository.completeAttempt(
                attemptId = attemptId,
                score = scorePercentage,
                timeSpentSeconds = timeSpentSeconds,
                passingScore = currentQuiz.passingScore
            )
            
            // Update UI
            _uiState.update {
                it.copy(
                    isQuizActive = false,
                    showResults = true,
                    score = scorePercentage,
                    correctCount = correctCount
                )
            }
        }
    }
    
    /**
     * Exit quiz
     */
    fun exitQuiz() {
        timerJob?.cancel()
        _uiState.update {
            QuizUiState(quizzes = it.quizzes)
        }
    }
    
    /**
     * Get user answer for a question
     */
    fun getUserAnswer(questionId: String): String? {
        return _uiState.value.userAnswers[questionId]
    }
    
    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
