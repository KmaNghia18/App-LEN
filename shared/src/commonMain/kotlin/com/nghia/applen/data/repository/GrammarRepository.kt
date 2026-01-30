package com.nghia.applen.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.nghia.applen.db.AppDatabase
import com.nghia.applen.model.Exercise
import com.nghia.applen.model.ExerciseType
import com.nghia.applen.model.Grammar
import com.nghia.applen.model.GrammarLevel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString
import kotlin.coroutines.CoroutineContext

class GrammarRepository(
    private val database: AppDatabase,
    private val dispatcher: CoroutineContext = Dispatchers.Default
) {
    
    private val queries = database.appDatabaseQueries
    
    /**
     * Get all grammar lessons
     */
    fun getAllGrammar(): Flow<List<Grammar>> {
        return queries.selectAllGrammar()
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToGrammar(it) }
            }
    }
    
    /**
     * Get grammar by ID
     */
    suspend fun getGrammarById(id: String): Grammar? {
        val entity = queries.selectGrammarById(id).executeAsOneOrNull() ?: return null
        return mapToGrammar(entity)
    }
    
    /**
     * Get grammar by category
     */
    fun getByCategory(category: String): Flow<List<Grammar>> {
        return queries.selectByCategory(category)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToGrammar(it) }
            }
    }
    
    /**
     * Get completed lessons
     */
    fun getCompleted(): Flow<List<Grammar>> {
        return queries.selectCompleted()
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToGrammar(it) }
            }
    }
    
    /**
     * Insert or update grammar lesson
     */
    suspend fun insertGrammar(grammar: Grammar) {
        queries.insertGrammar(
            id = grammar.id,
            title = grammar.title,
            category = grammar.category,
            level = grammar.level.name,
            content = grammar.content,
            estimatedMinutes = grammar.estimatedMinutes.toLong(),
            isCompleted = grammar.isCompleted,
            completedAt = null,
            createdAt = System.currentTimeMillis()
        )
        
        // Insert exercises
        grammar.exercises.forEach { exercise ->
            queries.insertExercise(
                id = exercise.id,
                grammarId = grammar.id,
                question = exercise.question,
                type = exercise.type.name,
                correctAnswer = exercise.correctAnswer,
                options = Json.encodeToString(exercise.options),
                explanation = exercise.explanation
            )
        }
    }
    
    /**
     * Mark lesson as completed
     */
    suspend fun markAsCompleted(id: String) {
        queries.markAsCompleted(
            completedAt = System.currentTimeMillis(),
            id = id
        )
    }
    
    /**
     * Delete grammar lesson
     */
    suspend fun deleteGrammar(id: String) {
        queries.deleteGrammar(id)
    }
    
    /**
     * Map database entity to domain model
     */
    private fun mapToGrammar(entity: com.nghia.applen.db.Grammar): Grammar {
        val exercises = queries.selectExercisesByGrammarId(entity.id)
            .executeAsList()
            .map { exerciseEntity ->
                Exercise(
                    id = exerciseEntity.id,
                    question = exerciseEntity.question,
                    type = ExerciseType.valueOf(exerciseEntity.type),
                    correctAnswer = exerciseEntity.correctAnswer,
                    options = Json.decodeFromString(exerciseEntity.options),
                    explanation = exerciseEntity.explanation
                )
            }
        
        return Grammar(
            id = entity.id,
            title = entity.title,
            category = entity.category,
            level = GrammarLevel.valueOf(entity.level),
            content = entity.content,
            estimatedMinutes = entity.estimatedMinutes.toInt(),
            exercises = exercises,
            isCompleted = entity.isCompleted
        )
    }
}
