package com.nghia.applen.data.repository

import com.nghia.applen.data.api.VocabularyApiService
import com.nghia.applen.data.local.DatabaseDriverFactory
import com.nghia.applen.data.mock.MockVocabularyData
import com.nghia.applen.db.AppDatabase
import com.nghia.applen.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Helper to seed database with sample data
 */
class DatabaseSeeder(
    private val database: AppDatabase,
    private val vocabularyRepository: VocabularyRepository
) {
    
    /**
     * Seed database with mock vocabulary data
     */
    suspend fun seedVocabulary() {
        val existingCount = database.appDatabaseQueries.selectAll().executeAsList().size
        
        if (existingCount == 0) {
            // Database is empty, seed with mock data
            val mockData = MockVocabularyData.getSampleVocabulary()
            
            mockData.forEach { vocabulary ->
                vocabularyRepository.insertVocabulary(vocabulary)
            }
            
            println("✅ Seeded database with ${mockData.size} vocabulary words")
        } else {
            println("ℹ️ Database already contains $existingCount words, skipping seed")
        }
    }
    
    /**
     * Clear all vocabulary data
     */
    suspend fun clearVocabulary() {
        database.appDatabaseQueries.transaction {
            database.appDatabaseQueries.selectAll().executeAsList().forEach {
                database.appDatabaseQueries.deleteById(it.id)
            }
        }
        println("🗑️ Cleared all vocabulary data")
    }
    
    /**
     * Get database statistics
     */
    fun getDatabaseStats(): DatabaseStats {
        val totalWords = database.appDatabaseQueries.selectAll().executeAsList().size
        val favorites = database.appDatabaseQueries.selectFavorites().executeAsList().size
        val dueCards = database.appDatabaseQueries.selectDueCards(System.currentTimeMillis())
            .executeAsList().size
        
        return DatabaseStats(
            totalWords = totalWords,
            favorites = favorites,
            dueCards = dueCards
        )
    }
    
    data class DatabaseStats(
        val totalWords: Int,
        val favorites: Int,
        val dueCards: Int
    )
}
