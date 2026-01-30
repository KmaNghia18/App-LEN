# App-LEN - English Learning Application 📚

A modern English learning app for TOEIC/IELTS preparation built with Kotlin Multiplatform and Jetpack Compose.

## Tech Stack

- **Kotlin Multiplatform Mobile (KMM)** - Share business logic across platforms
- **Jetpack Compose** - Modern declarative UI for Android
- **Material 3** - Latest Material Design
- **Ktor** - Networking
- **SQLDelight** - Type-safe local database
- **Koin** - Dependency injection

## Features

✨ **Vocabulary Learning**
- Flashcard system with spaced repetition
- Audio pronunciation
- Topic-based organization

📖 **Grammar Lessons**
- Interactive lessons with examples
- Practice exercises

📝 **Practice Tests**
- TOEIC/IELTS format questions
- Reading & Listening sections
- Timed tests with score analysis

📊 **Progress Tracking**
- Daily goals and study streaks
- Detailed statistics and charts
- Performance analytics

🔊 **Offline Support**
- Study without internet
- Background sync when online

## Project Structure

```
App-LEN/
├── shared/          # Kotlin Multiplatform shared module
├── androidApp/      # Android application
├── build.gradle.kts
└── settings.gradle.kts
```

## Requirements

- Android Studio Hedgehog (2023.1.1) or later
- JDK 17 or later
- Android SDK 24+

## Setup

1. Clone the repository:
```bash
git clone https://github.com/KmaNghia18/App-LEN.git
cd App-LEN
```

2. Open the project in Android Studio

3. Sync Gradle files

4. Run the app on an emulator or device

## Build

```bash
# Build Android app
./gradlew :androidApp:assembleDebug

# Run tests
./gradlew test
```

## License

MIT License

## Author

KmaNghia18
