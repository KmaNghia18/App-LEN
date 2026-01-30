package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * User progress that syncs across devices
 */
@Serializable
data class SyncData(
    val userId: String,
    val lastSyncAt: Long,
    val vocabulary: VocabularyProgress,
    val grammar: GrammarProgress,
    val quizzes: QuizProgress,
    val settings: UserSettings,
    val achievements: List<Achievement>
)

@Serializable
data class VocabularyProgress(
    val knownWords: List<String>, // Word IDs
    val favoriteWords: List<String>,
    val reviewSchedule: Map<String, Long>, // wordId -> nextReviewTime
    val masteryLevels: Map<String, Int> // wordId -> level (0-5)
)

@Serializable
data class GrammarProgress(
    val completedLessons: List<String>, // Lesson IDs
    val scores: Map<String, Int>, // lessonId -> score
    val lastStudied: Map<String, Long> // lessonId -> timestamp
)

@Serializable
data class QuizProgress(
    val attempts: List<QuizAttempt>,
    val bestScores: Map<String, Int>, // quizId -> best score
    val totalTimeSpent: Int, // in minutes
    val streak: StreakData
)

@Serializable
data class StreakData(
    val currentStreak: Int,
    val longestStreak: Int,
    val lastStudyDate: Long,
    val studyDates: List<Long> // Last 30 days
)

@Serializable
data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val icon: String,
    val unlockedAt: Long,
    val category: AchievementCategory
)

@Serializable
enum class AchievementCategory {
    VOCABULARY,
    GRAMMAR,
    QUIZ,
    STREAK,
    SOCIAL
}

@Serializable
data class QuizAttempt(
    val id: String,
    val quizId: String,
    val userId: String,
    val answers: Map<String, Int>,
    val score: Int,
    val totalQuestions: Int,
    val startedAt: Long,
    val completedAt: Long,
    val timeTaken: Int
)
