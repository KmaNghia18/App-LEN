package com.nghia.applen.data.repository

import com.nghia.applen.db.AppDatabase
import com.nghia.applen.model.Vocabulary
import com.nghia.applen.model.Definition
import com.nghia.applen.domain.SpacedRepetitionEngine
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class VocabularyRepository(
    private val database: AppDatabase,
    private val spacedRepetitionEngine: SpacedRepetitionEngine,
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
                entities.map { entity ->
                    mapToVocabulary(entity)
                }
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
     * Get due cards for review
     */
    fun getDueCards(): Flow<List<Vocabulary>> {
        val currentTime = System.currentTimeMillis()
        return queries.selectDueCards(currentTime)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { entity ->
                    mapToVocabulary(entity)
                }
            }
    }
    
    /**
     * Get vocabulary by topic
     */
    fun getByTopic(topic: String): Flow<List<Vocabulary>> {
        return queries.selectByTopic(topic)
            .asFlow()
            .mapToList(dispatcher)
            .map { entities ->
                entities.map { entity ->
                    mapToVocabulary(entity)
                }
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
                entities.map { entity ->
                    mapToVocabulary(entity)
                }
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
                entities.map { entity ->
                    mapToVocabulary(entity)
                }
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
                entities.map { entity ->
                    mapToVocabulary(entity)
                }
            }
    }
    
    /**
     * Insert or update vocabulary
     */
    suspend fun insertVocabulary(vocabulary: Vocabulary) {
        queries.insert(
            id = vocabulary.id,
            word = vocabulary.word,
            phonetic = vocabulary.phonetic,
            audioUrl = vocabulary.audioUrl,
            partOfSpeech = vocabulary.partOfSpeech,
            level = vocabulary.level.name,
            topic = vocabulary.topic,
            isFavorite = vocabulary.isFavorite,
            masteryLevel = vocabulary.masteryLevel.toLong(),
            lastReviewedAt = vocabulary.lastReviewedAt,
            nextReviewAt = vocabulary.nextReviewAt,
            efactor = vocabulary.masteryLevel.toDouble(), // Store as efactor
            reviewCount = vocabulary.masteryLevel.toLong(),
            createdAt = System.currentTimeMillis()
        )
        
        // Insert definitions
        vocabulary.definitions.forEach { definition ->
            queries.insertDefinition(
                id = "${vocabulary.id}_def_${definition.hashCode()}",
                vocabularyId = vocabulary.id,
                meaning = definition.meaning,
                example = definition.example
            )
        }
        
        // Insert examples
        vocabulary.examples.forEach { example ->
            queries.insertExample(
                id = "${vocabulary.id}_ex_${example.hashCode()}",
                vocabularyId = vocabulary.id,
                sentence = example,
                translation = null
            )
        }
        
        // Insert synonyms
        vocabulary.synonyms.forEach { synonym ->
            queries.insertRelatedWord(
                id = "${vocabulary.id}_syn_${synonym.hashCode()}",
                vocabularyId = vocabulary.id,
                word = synonym,
                type = "synonym"
            )
        }
        
        // Insert antonyms
        vocabulary.antonyms.forEach { antonym ->
            queries.insertRelatedWord(
                id = "${vocabulary.id}_ant_${antonym.hashCode()}",
                vocabularyId = vocabulary.id,
                word = antonym,
                type = "antonym"
            )
        }
    }
    
    /**
     * Review a card and update mastery level using spaced repetition
     */
    suspend fun reviewCard(id: String, knows: Boolean) {
        val vocab = getVocabularyById(id) ?: return
        
        val quality = spacedRepetitionEngine.getQualityFromResponse(knows)
        val result = spacedRepetitionEngine.calculateNextReview(
            quality = quality,
            previousInterval = calculateIntervalDays(vocab.lastReviewedAt, vocab.nextReviewAt),
            previousEFactor = 2.5f, // TODO: Store efactor in DB
            reviewCount = vocab.reviewCount
        )
        
        val newMasteryLevel = if (result.shouldReset) 0 else (vocab.masteryLevel + 1).coerceAtMost(5)
        
        queries.updateMastery(
            masteryLevel = newMasteryLevel.toLong(),
            lastReviewedAt = System.currentTimeMillis(),
            nextReviewAt = result.nextReviewDate,
            efactor = result.newEFactor.toDouble(),
            reviewCount = (vocab.reviewCount + 1).toLong(),
            id = id
        )
    }
    
    /**
     * Toggle favorite status
     */
    suspend fun toggleFavorite(id: String) {
        val vocab = getVocabularyById(id) ?: return
        queries.toggleFavorite(!vocab.isFavorite, id)
    }
    
    /**
     * Delete vocabulary
     */
    suspend fun deleteVocabulary(id: String) {
        queries.deleteById(id)
    }
    
    /**
     * Helper to calculate interval in days
     */
    private fun calculateIntervalDays(lastReview: Long?, nextReview: Long?): Int {
        if (lastReview == null || nextReview == null) return 0
        val diffMillis = nextReview - lastReview
        return (diffMillis / (24 * 60 * 60 * 1000)).toInt()
    }
    
    /**
     * Map database entity to domain model
     */
    private fun mapToVocabulary(entity: com.nghia.applen.db.Vocabulary): Vocabulary {
        val definitions = queries.selectDefinitionsByVocabId(entity.id)
            .executeAsList()
            .map { Definition(it.meaning, it.example) }
        
        val examples = queries.selectExamplesByVocabId(entity.id)
            .executeAsList()
            .map { it.sentence }
        
        val synonyms = queries.selectSynonyms(entity.id)
            .executeAsList()
            .map { it.word }
        
        val antonyms = queries.selectAntonyms(entity.id)
            .executeAsList()
            .map { it.word }
        
        return Vocabulary(
            id = entity.id,
            word = entity.word,
            phonetic = entity.phonetic,
            audioUrl = entity.audioUrl,
            partOfSpeech = entity.partOfSpeech,
            definitions = definitions,
            examples = examples,
            synonyms = synonyms,
            antonyms = antonyms,
            level = com.nghia.applen.model.VocabularyLevel.valueOf(entity.level),
            topic = entity.topic,
            isFavorite = entity.isFavorite,
            masteryLevel = entity.masteryLevel.toInt(),
            lastReviewedAt = entity.lastReviewedAt,
            nextReviewAt = entity.nextReviewAt
        )
    }
}
