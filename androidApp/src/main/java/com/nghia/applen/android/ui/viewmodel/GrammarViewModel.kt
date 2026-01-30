package com.nghia.applen.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nghia.applen.data.repository.GrammarRepository
import com.nghia.applen.model.Grammar
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class GrammarUiState(
    val grammarList: List<Grammar> = emptyList(),
    val completedLessons: List<Grammar> = emptyList(),
    val selectedCategory: String? = null,
    val currentLesson: Grammar? = null,
    val exerciseResults: Map<String, Boolean> = emptyMap(), // exerciseId to isCorrect
    val isLoading: Boolean = false,
    val error: String? = null
)

class GrammarViewModel(
    private val repository: GrammarRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(GrammarUiState())
    val uiState: StateFlow<GrammarUiState> = _uiState.asStateFlow()
    
    init {
        loadGrammar()
        loadCompleted()
    }
    
    private fun loadGrammar() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            repository.getAllGrammar()
                .catch { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message ?: "Unknown error"
                        )
                    }
                }
                .collect { grammarList ->
                    _uiState.update {
                        it.copy(
                            grammarList = grammarList,
                            isLoading = false,
                            error = null
                        )
                    }
                }
        }
    }
    
    private fun loadCompleted() {
        viewModelScope.launch {
            repository.getCompleted()
                .collect { completed ->
                    _uiState.update { it.copy(completedLessons = completed) }
                }
        }
    }
    
    fun filterByCategory(category: String?) {
        _uiState.update { it.copy(selectedCategory = category) }
        
        if (category == null) {
            loadGrammar()
            return
        }
        
        viewModelScope.launch {
            repository.getByCategory(category)
                .collect { lessons ->
                    _uiState.update { it.copy(grammarList = lessons) }
                }
        }
    }
    
    fun selectLesson(lessonId: String) {
        viewModelScope.launch {
            val lesson = repository.getGrammarById(lessonId)
            _uiState.update {
                it.copy(
                    currentLesson = lesson,
                    exerciseResults = emptyMap()
                )
            }
        }
    }
    
    fun submitAnswer(exerciseId: String, userAnswer: String) {
        val currentLesson = _uiState.value.currentLesson ?: return
        val exercise = currentLesson.exercises.find { it.id == exerciseId } ?: return
        
        val isCorrect = userAnswer.equals(exercise.correctAnswer, ignoreCase = true)
        
        _uiState.update { state ->
            state.copy(
                exerciseResults = state.exerciseResults + (exerciseId to isCorrect)
            )
        }
    }
    
    fun completeLesson(lessonId: String) {
        viewModelScope.launch {
            repository.markAsCompleted(lessonId)
            // Reload to update UI
            loadGrammar()
            loadCompleted()
        }
    }
    
    fun getCategoryStats(): Map<String, Int> {
        return _uiState.value.grammarList
            .groupBy { it.category }
            .mapValues { it.value.size }
    }
}
