package com.nghia.applen.di

import com.nghia.applen.data.api.ApiClient
import com.nghia.applen.data.api.VocabularyApiService
import com.nghia.applen.data.local.DatabaseDriverFactory
import com.nghia.applen.data.repository.DatabaseSeeder
import com.nghia.applen.data.repository.VocabularyRepository
import com.nghia.applen.data.repository.GrammarRepository
import com.nghia.applen.data.repository.QuizRepository
import com.nghia.applen.db.AppDatabase
import com.nghia.applen.domain.SpacedRepetitionEngine
import org.koin.dsl.module

val databaseModule = module {
    single { DatabaseDriverFactory(get()).createDriver() }
    single { AppDatabase(get()) }
}

val networkModule = module {
    single { ApiClient.createHttpClient() }
    single { VocabularyApiService(get()) }
}

val domainModule = module {
    single { SpacedRepetitionEngine() }
}

val dataModule = module {
    single { VocabularyRepository(get()) }
    single { GrammarRepository(get()) }
    single { QuizRepository(get()) }
    // DatabaseSeeder will be created in the Android app module with context
    // single { DatabaseSeeder(get(), get(), get(), get()) }
}
