package com.nghia.applen.android.di

import com.nghia.applen.android.ui.viewmodel.GrammarViewModel
import com.nghia.applen.android.ui.viewmodel.QuizViewModel
import com.nghia.applen.android.ui.viewmodel.VocabularyViewModel
import com.nghia.applen.android.ui.viewmodel.ProgressViewModel
import com.nghia.applen.android.ui.viewmodel.ThemeViewModel
import com.nghia.applen.di.dataModule
import com.nghia.applen.di.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { VocabularyViewModel(get()) }
    viewModel { GrammarViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { ProgressViewModel() }
    single { ThemeViewModel() }
}

val allModules = listOf(
    domainModule,
    dataModule,
    androidModule
)
