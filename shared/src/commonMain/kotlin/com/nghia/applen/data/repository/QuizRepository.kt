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
    
    private val queries = database.quizDatabaseQueries
    
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
    fun getQuizzesByType(type: String): Flow<List<Quiz>> {
        return queries.selectQuizzesByType(type)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToQuiz(it) }
            }
    }
    
    /**
     * Insert or update quiz
     */
    suspend fun insertQuiz(quiz: Quiz) {
        queries.insertQuiz(
            id = quiz.id,
            title = quiz.title,
            description = quiz.description,
            type = quiz.type.name,
            section = quiz.section.name,
            duration = quiz.duration.toLong(),
            passingScore = quiz.passingScore.toLong(),
            totalPoints = quiz.totalPoints.toLong(),
            questions = Json.encodeToString(quiz.questions),
            createdAt = System.currentTimeMillis()
        )
    }
    
    /**
     * Delete quiz
     */
    suspend fun deleteQuiz(id: String) {
        queries.deleteQuiz(id)
    }
    
    /**
     * Save quiz attempt
     */
    suspend fun saveAttempt(attempt: QuizAttempt) {
        queries.insertAttempt(
            id = attempt.id,
            quizId = attempt.quizId,
            userId = attempt.userId,
            score = attempt.score.toLong(),
            totalPoints = attempt.totalPoints.toLong(),
            answers = Json.encodeToString(attempt.answers),
            timeTaken = attempt.timeTaken.toLong(),
            completedAt = attempt.completedAt
        )
    }
    
    /**
     * Get recent attempts
     */
    suspend fun getRecentAttempts(limit: Int): List<QuizAttempt> {
        return queries.selectRecentAttempts(limit.toLong())
            .executeAsList()
            .map { mapToAttempt(it) }
    }
    
    /**
     * Get attempts by quiz ID
     */
    suspend fun getAttemptsByQuizId(quizId: String): List<QuizAttempt> {
        return queries.selectAttemptsByQuizId(quizId)
            .executeAsList()
            .map { mapToAttempt(it) }
    }
    
    /**
     * Map database entity to domain model
     */
    private fun mapToQuiz(entity: com.nghia.applen.db.Quiz): Quiz {
        val questions = try {
            Json.decodeFromString<List<Question>>(entity.questions)
        } catch (e: Exception) {
            emptyList()
        }
        
        return Quiz(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            type = QuizType.valueOf(entity.type),
            section = QuizSection.valueOf(entity.section),
            duration = entity.duration.toInt(),
            questions = questions,
            passingScore = entity.passingScore.toInt(),
            totalPoints = entity.totalPoints.toInt()
        )
    }
    
    /**
     * Map database entity to QuizAttempt
     */
    private fun mapToAttempt(entity: com.nghia.applen.db.QuizAttempt): QuizAttempt {
        val answers = try {
            Json.decodeFromString<Map<String, Int>>(entity.answers)
        } catch (e: Exception) {
            emptyMap()
        }
        
        return QuizAttempt(
            id = entity.id,
            quizId = entity.quizId,
            userId = entity.userId,
            score = entity.score.toInt(),
            totalPoints = entity.totalPoints.toInt(),
            answers = answers,
            timeTaken = entity.timeTaken.toInt(),
            startedAt = entity.completedAt, // Using completedAt as startedAt for now
            completedAt = entity.completedAt
        )
    }
}
