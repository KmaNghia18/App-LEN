# App-LEN - English Learning App 🎓

A comprehensive English learning application for TOEIC and IELTS preparation, built with Kotlin Multiplatform and Jetpack Compose.

![Version](https://img.shields.io/badge/version-1.0.0-blue)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9+-purple)
![Android](https://img.shields.io/badge/Android-7.0+-green)
![License](https://img.shields.io/badge/license-MIT-orange)

## 🚀 Features

### ✅ Implemented & Production Ready

- **📖 Vocabulary System**
  - 10 sample words with definitions
  - Flashcards with smooth flip animation
  - Swipe gestures (know/don't know)
  - Audio pronunciation support
  - Search and filter by category, level
  - Bookmark favorite words
  
- **📚 Grammar Lessons**
  - 5 comprehensive lessons (105 min content)
  - Markdown content viewer
  - Interactive exercises with instant feedback
  - Progress tracking per lesson
  - Categories: Beginner to Advanced

- **🎯 Quiz & Tests**
  - 6 complete tests (56 questions total)
  - **Reading**: TOEIC, IELTS, Grammar, Vocabulary (45 Q)
  - **Listening**: TOEIC Part 1, IELTS Section 1 (11 Q)
  - Real-time countdown timer with pulsing animation
  - Audio player for listening questions
  - Image support for visual questions
  - Auto-submit on timeout
  - Instant results with pass/fail

- **📊 Progress Analytics**
  - Study streak tracking with fire emoji
  - Average score calculation
  - 7-day study time bar chart with animations
  - Recent quiz scores with color coding
  - Total words and tests completed

- **👋 Onboarding**
  - 4-page introduction flow with HorizontalPager
  - Goal selection (TOEIC/IELTS/General)
  - Feature showcase with emojis
  - Page indicators

- **⚙️ Settings**
  - Theme switching (Light/Dark/System) with real-time updates
  - Notification preferences (daily, streak reminders)
  - Learning settings (auto-play audio)
  - About section

- **🔐 Authentication**
  - Login screen with email/password
  - Guest access option
  - Profile screen with user stats
  - Account management options

- **🎵 Audio & Media**
  - Audio player component (play/pause, progress)
  - Image viewer for quiz questions
  - Listening question player (audio + image)
  - Auto-play support

- **✨ UI Polish**
  - Material 3 Design throughout
  - Smooth animations and transitions
  - Loading states (pulsing, shimmer, success, error)
  - Theme persistence with ViewModel
  - 3D flip card animation

## 🏗️ Architecture

**Tech Stack:**
- Kotlin Multiplatform
- Jetpack Compose (Android UI)
- SQLDelight (Local database)
- Ktor (API client)
- Koin (Dependency injection)
- Material 3

**Pattern:**
- MVVM (Model-View-ViewModel)
- Repository pattern
- Clean Architecture
- Reactive programming (Kotlin Flow)

## 📁 Project Structure

```
KMP/
├── shared/                 # Shared Kotlin code
│   ├── commonMain/
│   │   ├── kotlin/
│   │   │   ├── data/      # Repositories, API, Mock data
│   │   │   ├── domain/    # Business logic
│   │   │   ├── di/        # Koin modules
│   │   │   └── model/     # Data models
│   │   └── sqldelight/    # Database schemas
│   └── androidMain/       # Android-specific
│
└── androidApp/            # Android UI
    └── src/main/java/
        ├── di/            # DI configuration
        ├── ui/
        │   ├── components/  # Reusable components
        │   ├── screens/     # Screen composables
        │   ├── theme/       # Material 3 theme
        │   └── viewmodel/   # ViewModels
        └── MainActivity.kt
```

## 🚀 Getting Started

### Prerequisites
- Android Studio Hedgehog or later
- JDK 17+
- Android SDK 34+
- Kotlin 1.9+

### Installation

1. **Clone the repository**
```bash
git clone https://github.com/KmaNghia18/App-LEN.git
cd App-LEN
```

2. **Open in Android Studio**
- File → Open → Select the KMP folder

3. **Sync Gradle**
- Wait for Gradle sync to complete

4. **Run the app**
- Select `androidApp` configuration
- Click Run (or press Shift+F10)

### First Launch
- Database automatically seeds with sample data
- Complete onboarding flow
- Choose your learning goal
- Start learning!

## 📊 Database

**Auto-seeded content**:
- 10 vocabulary words (Business, Travel, Academic)
- 5 grammar lessons (105 minutes total)
- 6 quiz tests (56 questions):
  - 4 reading tests (45 questions)
  - 2 listening tests (11 questions)

**Tables**:
- Vocabulary (with audio URLs)
- Grammar (with exercises)
- Quiz, Question (with audio/image support)
- QuizAttempt, UserAnswer
- User settings

## 🎯 Usage

### Study Vocabulary
1. Home → Vocabulary
2. Swipe cards or tap to flip
3. Mark as known/unknown
4. Track progress

### Learn Grammar
1. Home → Grammar
2. Select a lesson
3. Read content (Markdown)
4. Practice exercises
5. View score and feedback

### Take Quiz
1. Home → Practice Tests
2. Select a quiz
3. Answer questions (timed)
4. Submit for instant results
5. Review score and try again

### Track Progress
1. Bottom nav → Progress
2. View streak, stats
3. Check study time chart
4. Review recent scores

### Change Theme
1. Home → Settings icon
2. Select theme (Light/Dark/System)
3. Theme updates instantly

## 📝 Development

### Code Statistics
- **Total commits**: 22
- **Lines of code**: 7,800+
- **Files**: 43+
- **Kotlin**: 100%

### Feature Branches (All Merged)
- ✅ `feature/grammar-and-quiz` (16 commits)
- ✅ `feature/progress-analytics` (1 commit)
- ✅ `feature/onboarding-flow` (1 commit)
- ✅ `feature/ui-polish` (1 commit)

## 🔮 Roadmap

### Next Features
- [ ] Audio pronunciation
- [ ] Listening tests
- [ ] Image support for questions
- [ ] User authentication
- [ ] Cloud sync
- [ ] Social features

### Future Enhancements
- [ ] AI-powered learning
- [ ] Speaking practice
- [ ] Writing assessment
- [ ] iOS app (using KMP)

## 🤝 Contributing

Contributions welcome! Please:
1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License.

## 👨‍💻 Author

**KmaNghia18**
- GitHub: [@KmaNghia18](https://github.com/KmaNghia18)
- Repository: [App-LEN](https://github.com/KmaNghia18/App-LEN)

## 🙏 Acknowledgments

- Material 3 Design Guidelines
- Jetpack Compose documentation
- Kotlin Multiplatform community
- SQLDelight library

---

**Built with ❤️ using Kotlin Multiplatform**

Last Updated: January 30, 2026
