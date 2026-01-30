package com.nghia.applen.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.nghia.applen.db.AppDatabase
import com.nghia.applen.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlin.coroutines.CoroutineContext

class QuizRepository(
    private val database: AppDatabase,
    private val dispatcher: CoroutineContext = Dispatchers.Default
) {
    
    private val queries = database.appDatabaseQueries
    
    /**
     * Get all quizzes
     */
    fun getAllQuizzes(): Flow<List<Quiz>> {
        return queries.selectAllQuizzes()
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToQuiz(it) }
            }
    }
    
    /**
     * Get quiz by ID
     */
    suspend fun getQuizById(id: String): Quiz? {
        val entity = queries.selectQuizById(id).executeAsOneOrNull() ?: return null
        return mapToQuiz(entity)
    }
    
    /**
     * Get quizzes by type
     */
    fun getQuizzesByType(type: QuizType): Flow<List<Quiz>> {
        return queries.selectQuizzesByType(type.name)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToQuiz(it) }
            }
    }
    
    /**
     * Insert quiz with questions
     */
    suspend fun insertQuiz(quiz: Quiz) {
        queries.insertQuiz(
            id = quiz.id,
            title = quiz.title,
            description = quiz.description,
            type = quiz.type.name,
            level = quiz.level.name,
            totalQuestions = quiz.totalQuestions.toLong(),
            timeLimitMinutes = quiz.timeLimitMinutes.toLong(),
            passingScore = quiz.passingScore.toLong(),
            createdAt = System.currentTimeMillis()
        )
        
        // Insert questions
        quiz.questions.forEach { question ->
            queries.insertQuestion(
                id = question.id,
                quizId = quiz.id,
                questionNumber = question.questionNumber.toLong(),
                questionText = question.questionText,
                questionType = question.questionType.name,
                options = Json.encodeToString(question.options),
                correctAnswer = question.correctAnswer,
                explanation = question.explanation,
                points = question.points.toLong()
            )
        }
    }
    
    /**
     * Start a new quiz attempt
     */
    suspend fun startAttempt(quizId: String, totalQuestions: Int): String {
        val attemptId = "attempt_${System.currentTimeMillis()}"
        queries.insertAttempt(
            id = attemptId,
            quizId = quizId,
            startedAt = System.currentTimeMillis(),
            completedAt = null,
            score = 0,
            totalQuestions = totalQuestions.toLong(),
            timeSpentSeconds = 0,
            isPassed = false
        )
        return attemptId
    }
    
    /**
     * Submit answer for a question
     */
    suspend fun submitAnswer(
        attemptId: String,
        questionId: String,
        userAnswer: String,
        correctAnswer: String
    ) {
        val isCorrect = userAnswer.equals(correctAnswer, ignoreCase = true)
        
        queries.insertUserAnswer(
            id = "answer_${System.currentTimeMillis()}_${questionId}",
            attemptId = attemptId,
            questionId = questionId,
            userAnswer = userAnswer,
            isCorrect = isCorrect,
            answeredAt = System.currentTimeMillis()
        )
    }
    
    /**
     * Complete quiz attempt
     */
    suspend fun completeAttempt(
        attemptId: String,
        score: Int,
        timeSpentSeconds: Int,
        passingScore: Int
    ) {
        val isPassed = score >= passingScore
        
        queries.updateAttempt(
            completedAt = System.currentTimeMillis(),
            score = score.toLong(),
            timeSpentSeconds = timeSpentSeconds.toLong(),
            isPassed = isPassed,
            id = attemptId
        )
    }
    
    /**
     * Get recent attempts
     */
    suspend fun getRecentAttempts(limit: Int = 10): List<QuizAttempt> {
        return queries.selectRecentAttempts(limit.toLong())
            .executeAsList()
            .map { mapToQuizAttempt(it) }
    }
    
    /**
     * Get attempts for a specific quiz
     */
    suspend fun getAttemptsByQuizId(quizId: String): List<QuizAttempt> {
        return queries.selectAttemptsByQuizId(quizId)
            .executeAsList()
            .map { mapToQuizAttempt(it) }
    }
    
    /**
     * Map database entity to domain model
     */
    private fun mapToQuiz(entity: com.nghia.applen.db.Quiz): Quiz {
        val questions = queries.selectQuestionsByQuizId(entity.id)
            .executeAsList()
            .map { questionEntity ->
                Question(
                    id = questionEntity.id,
                    questionNumber = questionEntity.questionNumber.toInt(),
                    questionText = questionEntity.questionText,
                    questionType = QuestionType.valueOf(questionEntity.questionType),
                    options = Json.decodeFromString(questionEntity.options),
                    correctAnswer = questionEntity.correctAnswer,
                    explanation = questionEntity.explanation,
                    points = questionEntity.points.toInt()
                )
            }
        
        return Quiz(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            type = QuizType.valueOf(entity.type),
            level = QuizLevel.valueOf(entity.level),
            totalQuestions = entity.totalQuestions.toInt(),
            timeLimitMinutes = entity.timeLimitMinutes.toInt(),
            passingScore = entity.passingScore.toInt(),
            questions = questions
        )
    }
    
    private fun mapToQuizAttempt(entity: com.nghia.applen.db.QuizAttempt): QuizAttempt {
        return QuizAttempt(
            id = entity.id,
            quizId = entity.quizId,
            startedAt = entity.startedAt,
            completedAt = entity.completedAt,
            score = entity.score.toInt(),
            totalQuestions = entity.totalQuestions.toInt(),
            timeSpentSeconds = entity.timeSpentSeconds.toInt(),
            isPassed = entity.isPassed
        )
    }
}
