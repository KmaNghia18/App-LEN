package com.nghia.applen.data.repository

import com.nghia.applen.data.api.VocabularyApiService
import com.nghia.applen.data.local.DatabaseDriverFactory
import com.nghia.applen.data.mock.MockVocabularyData
import com.nghia.applen.db.AppDatabase
import com.nghia.applen.model.Vocabulary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Helper to seed datpackage com.nghia.applen.data.repository

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
    data class DatabaseStats(
        val totalWords: Int,
        val favorites: Int,
        val dueCards: Int
    )
}
