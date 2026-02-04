plugins {
    // Kotlin Multiplatform - Updated to 1.9.24 for Compose Compiler 1.5.14 compatibility
    kotlin("multiplatform") version "1.9.24" apply false
    kotlin("plugin.serialization") version "1.9.24" apply false
    
    // Android
    id("com.android.application") version "8.13.2" apply false
    id("com.android.library") version "8.13.2" apply false
    
    // SQLDelight
    id("app.cash.sqldelight") version "2.0.1" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
