package com.nghia.applen.data.repository

import com.nghia.applen.data.mock.MockVocabularyData
import com.nghia.applen.data.mock.MockGrammarData
import com.nghia.applen.data.mock.MockQuizData

class DatabaseSeeder(
    private val vocabularyRepository: VocabularyRepository,
    private val grammarRepository: GrammarRepository,
    private val quizRepository: QuizRepository,
    private val context: Any
) {
    
    /**
     * Seeds the database with initial data if it's empty
     */
    suspend fun seedAll() {
        if (shouldSeed()) {
            println("Seeding database with initial data...")
            seedVocabulary()
            seedGrammar()
            seedQuizzes()
            markAsSeeded()
            println("Database seeding completed!")
        }
    }

    /**
     * Seed vocabulary data
     */
    suspend fun seedVocabulary() {
        val vocabularyList = MockVocabularyData.getSampleVocabulary()

        vocabularyList.forEach { vocabulary ->
            vocabularyRepository.insertVocabulary(vocabulary)
        }

        println("Seeded ${vocabularyList.size} vocabulary words")
    }
    
    /**
     * Seed grammar lessons
     */
    suspend fun seedGrammar() {
        val grammarLessons = MockGrammarData.getSampleGrammar()
        
        grammarLessons.forEach { grammar ->
            grammarRepository.insertGrammar(grammar)
        }
        
        println("Seeded ${grammarLessons.size} grammar lessons")
    }
    
    /**
     * Seed quiz tests
     */
    suspend fun seedQuizzes() {
        val quizzes = MockQuizData.getSampleQuizzes()
        
        quizzes.forEach { quiz ->
            quizRepository.insertQuiz(quiz)
        }
        
        println("Seeded ${quizzes.size} quizzes with ${quizzes.sumOf { it.questions.size }} total questions")
    }
    
    /**
     * Check if should seed
     */
    private fun shouldSeed(): Boolean {
        return !isSeeded()
    }
    
    /**
     * Check if database has been seeded
     */
    private fun isSeeded(): Boolean {
        // Use SharedPreferences on Android to track seeding
        return getSharedPreference("is_database_seeded", false)
    }
    
    /**
     * Mark database as seeded
     */
    private fun markAsSeeded() {
        setSharedPreference("is_database_seeded", true)
    }
    
    /**
     * Platform-specific shared preference getter
     */
    private fun getSharedPreference(key: String, defaultValue: Boolean): Boolean {
        return try {
            if (context is android.content.Context) {
                val prefs = context.getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
                prefs.getBoolean(key, defaultValue)
            } else {
                defaultValue
            }
        } catch (e: Exception) {
            defaultValue
        }
    }
    
    /**
     * Platform-specific shared preference setter
     */
    private fun setSharedPreference(key: String, value: Boolean) {
        try {
            if (context is android.content.Context) {
                val prefs = context.getSharedPreferences("app_prefs", android.content.Context.MODE_PRIVATE)
                prefs.edit().putBoolean(key, value).apply()
            }
        } catch (e: Exception) {
            // Handle silently
        }
    }
}
