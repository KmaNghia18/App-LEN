package com.nghia.applen.android

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppLENApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@AppLENApplication)
            // TODO: Add modules when created
            // modules(appModule, dataModule, domainModule)
        }
    }
}
