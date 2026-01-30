package com.nghia.applen.data.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.nghia.applen.db.AppDatabase
import com.nghia.applen.model.Exercise
import com.nghia.applen.model.Grammar
import com.nghia.applen.model.GrammarExample
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
    
    private val queries = database.grammarDatabaseQueries
    
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
            description = grammar.description,
            category = grammar.category,
            level = grammar.level.name,
            content = grammar.content,
            examples = Json.encodeToString(grammar.examples),
            exercises = Json.encodeToString(grammar.exercises),
            isCompleted = if (grammar.isCompleted) 1 else 0,
            createdAt = System.currentTimeMillis()
        )
    }
    
    /**
     * Mark lesson as completed
     */
    suspend fun markAsCompleted(id: String) {
        queries.markAsCompleted(id = id)
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
        val examples = try {
            Json.decodeFromString<List<GrammarExample>>(entity.examples)
        } catch (e: Exception) {
            emptyList()
        }
        
        val exercises = try {
            Json.decodeFromString<List<Exercise>>(entity.exercises)
        } catch (e: Exception) {
            emptyList()
        }
        
        return Grammar(
            id = entity.id,
            title = entity.title,
            description = entity.description,
            category = entity.category,
            level = GrammarLevel.valueOf(entity.level),
            content = entity.content,
            examples = examples,
            exercises = exercises,
            isCompleted = entity.isCompleted == 1L
        )
    }
}
