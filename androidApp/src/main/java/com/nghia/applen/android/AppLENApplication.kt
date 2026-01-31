package com.nghia.applen.android

import android.app.Application
import com.nghia.applen.android.di.allModules
import com.nghia.applen.data.repository.DatabaseSeeder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppLENApplication : Application() {
    
    private val seeder: DatabaseSeeder by inject()
    
    override fun onCreate() {
        super.onCreate()
        
        startKoin {
            androidLogger()
            androidContext(this@AppLENApplication)
            modules(allModules)
        }
        
        // Seed database with sample data on first launch
        CoroutineScope(Dispatchers.IO).launch {
            seeder.seedAll()
        }
    }
}
