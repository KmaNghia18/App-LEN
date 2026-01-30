package com.nghia.applen.data.repository

import com.nghia.applen.db.AppDatabase
import com.nghia.applen.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class VocabularyRepository(
    private val database: AppDatabase,
    private val dispatcher: CoroutineContext = Dispatchers.Default
) {
    
    private val queries = database.appDatabaseQueries
    
    /**
     * Get all vocabulary words
     */
    fun getAllVocabulary(): Flow<List<Vocabulary>> {
        return queries.selectAll()
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToVocabulary(it) }
            }
    }
    
    /**
     * Get vocabulary by ID
     */
    suspend fun getVocabularyById(id: String): Vocabulary? {
        val entity = queries.selectById(id).executeAsOneOrNull() ?: return null
        return mapToVocabulary(entity)
    }
    
    /**
     * Get vocabulary by category
     */
    fun getByCategory(category: String): Flow<List<Vocabulary>> {
        return queries.selectByCategory(category)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToVocabulary(it) }
            }
    }
    
    /**
     * Get vocabulary by level
     */
    fun getByLevel(level: String): Flow<List<Vocabulary>> {
        return queries.selectByLevel(level)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToVocabulary(it) }
            }
    }
    
    /**
     * Get favorite vocabulary
     */
    fun getFavorites(): Flow<List<Vocabulary>> {
        return queries.selectFavorites()
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToVocabulary(it) }
            }
    }
    
    /**
     * Search vocabulary by word
     */
    fun searchByWord(query: String): Flow<List<Vocabulary>> {
        return queries.searchByWord(query)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { mapToVocabulary(it) }
            }
    }
    
    /**
     * Insert or update vocabulary
     */
    suspend fun insertVocabulary(vocabulary: Vocabulary) {
        queries.insert(
            id = vocabulary.id,
            word = vocabulary.word,
            definition = vocabulary.definition,
            level = vocabulary.level,
            category = vocabulary.category,
            example = vocabulary.example,
            pronunciation = vocabulary.pronunciation,
            audioUrl = vocabulary.audioUrl,
            isFavorite = if (vocabulary.isFavorite) 1 else 0,
            lastReviewedAt = vocabulary.lastReviewedAt,
            easeFactor = vocabulary.easeFactor,
            interval = vocabulary.interval.toLong(),
            repetitions = vocabulary.repetitions.toLong(),
            createdAt = System.currentTimeMillis()
        )
    }
    
    /**
     * Toggle favorite status
     */
    suspend fun toggleFavorite(id: String) {
        val vocab = getVocabularyById(id) ?: return
        queries.toggleFavorite(
            isFavorite = if (vocab.isFavorite) 0 else 1,
            id = id
        )
    }
    
    /**
     * Update review data after studying
     */
    suspend fun updateReviewData(
        id: String,
        easeFactor: Double,
        interval: Int,
        repetitions: Int,
        nextReviewAt: Long
    ) {
        queries.updateReviewData(
            easeFactor = easeFactor,
            interval = interval.toLong(),
            repetitions = repetitions.toLong(),
            lastReviewedAt = System.currentTimeMillis(),
            id = id
        )
    }
    
    /**
     * Delete vocabulary
     */
    suspend fun deleteVocabulary(id: String) {
        queries.deleteById(id)
    }
    
    /**
     * Map database entity to domain model
     */
    private fun mapToVocabulary(entity: com.nghia.applen.db.Vocabulary): Vocabulary {
        return Vocabulary(
            id = entity.id,
            word = entity.word,
            definition = entity.definition,
            level = entity.level,
            category = entity.category,
            example = entity.example,
            pronunciation = entity.pronunciation,
            audioUrl = entity.audioUrl,
            isFavorite = entity.isFavorite == 1L,
            lastReviewedAt = entity.lastReviewedAt,
            easeFactor = entity.easeFactor ?: 2.5,
            interval = entity.interval?.toInt() ?: 0,
            repetitions = entity.repetitions?.toInt() ?: 0
        )
    }
}
