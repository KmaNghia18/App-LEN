package com.nghia.applen.android.di

import com.nghia.applen.android.ui.viewmodel.GrammarViewModel
import com.nghia.applen.android.ui.viewmodel.VocabularyViewModel
import com.nghia.applen.di.dataModule
import com.nghia.applen.di.databaseModule
import com.nghia.applen.di.domainModule
import com.nghia.applen.di.networkModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { VocabularyViewModel(get()) }
    viewModel { GrammarViewModel(get()) }
    viewModel { QuizViewModel(get()) }
}

val allModules = listOf(
    databaseModule,
    networkModule,
    domainModule,
    dataModule,
    androidModule
)
