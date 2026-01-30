package com.nghia.applen.android.di

import com.nghia.applen.data.repository.GrammarRepository
import com.nghia.applen.data.repository.QuizRepository
import com.nghia.applen.data.repository.SocialRepository
import com.nghia.applen.data.repository.SyncRepository
import com.nghia.applen.data.repository.VocabularyRepository
import com.nghia.applen.android.ui.viewmodel.GrammarViewModel
import com.nghia.applen.android.ui.viewmodel.ProgressViewModel
import com.nghia.applen.android.ui.viewmodel.QuizViewModel
import com.nghia.applen.android.ui.viewmodel.SocialViewModel
import com.nghia.applen.android.ui.viewmodel.ThemeViewModel
import com.nghia.applen.android.ui.viewmodel.VocabularyViewModel
import com.nghia.applen.di.databaseModule
import com.nghia.applen.di.dataModule
import com.nghia.applen.di.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { VocabularyViewModel(get()) }
    viewModel { GrammarViewModel(get()) }
    viewModel { QuizViewModel(get()) }
    viewModel { ProgressViewModel() }
    single { ThemeViewModel() }
    viewModel { SocialViewModel(get()) }
}

val allModules = listOf(
    databaseModule,
    domainModule,
    dataModule,
    appModule
)
