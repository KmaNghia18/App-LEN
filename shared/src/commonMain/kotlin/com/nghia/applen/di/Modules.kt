package com.nghia.applen.di

import com.nghia.applen.data.api.ApiClient
import com.nghia.applen.data.api.VocabularyApiService
import com.nghia.applen.data.local.DatabaseDriverFactory
import com.nghia.applen.data.repository.DatabaseSeeder
import com.nghia.applen.data.repository.VocabularyRepository
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
    single { VocabularyRepository(get(), get()) }
    single { GrammarRepository(get()) }
    single { QuizRepository(get()) }
    single { DatabaseSeeder(get(), get(), get(), get()) }
}
