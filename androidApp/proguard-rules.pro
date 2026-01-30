# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in Android SDK/tools/proguard/proguard-android.txt

# Keep Kotlin metadata
-keep class kotlin.Metadata { *; }

# Keep Ktor
-keep class io.ktor.** { *; }

# Keep SQLDelight
-keep class app.cash.sqldelight.** { *; }

# Keep Koin
-keep class org.koin.** { *; }
