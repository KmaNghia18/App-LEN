package com.nghia.applen.android.di

import com.nghia.applen.android.ui.viewmodel.VocabularyViewModel
import com.nghia.applen.di.dataModule
import com.nghia.applen.di.databaseModule
import com.nghia.applen.di.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { VocabularyViewModel(get()) }
}

val allModules = listOf(
    databaseModule,
    domainModule,
    dataModule,
    androidModule
)
