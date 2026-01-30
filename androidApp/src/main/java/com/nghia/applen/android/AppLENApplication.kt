package com.nghia.applen.android

import android.app.Application
import com.nghia.applen.android.di.allModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppLENApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@AppLENApplication)
            modules(allModules)
        }
    }
}
