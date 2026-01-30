package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * User progress tracking
 */
@Serializable
data class UserProgress(
    val userId: String,
    val vocabularyStats: VocabularyStats,
    val grammarStats: GrammarStats,
    val quizStats: QuizStats,
    val dailyGoal: DailyGoal,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val totalStudyTime: Long = 0, // in milliseconds
    val level: Int = 1,
    val experiencePoints: Int = 0
)

@Serializable
data class VocabularyStats(
    val totalWords: Int = 0,
    val masteredWords: Int = 0,
    val learningWords: Int = 0,
    val newWords: Int = 0,
    val reviewsDue: Int = 0
)

@Serializable
data class GrammarStats(
    val totalLessons: Int = 0,
    val completedLessons: Int = 0,
    val inProgressLessons: Int = 0
)

@Serializable
data class QuizStats(
    val totalQuizzes: Int = 0,
    val completedQuizzes: Int = 0,
    val averageScore: Double = 0.0,
    val bestScore: Int = 0,
    val totalTimeSpent: Long = 0 // in milliseconds
)

@Serializable
data class DailyGoal(
    val targetWords: Int = 20,
    val targetStudyTime: Int = 30, // in minutes
    val completedWords: Int = 0,
    val completedStudyTime: Int = 0,
    val lastUpdated: Long = 0
)

/**
 * Study session tracking
 */
@Serializable
data class StudySession(
    val id: String,
    val userId: String,
    val startTime: Long,
    val endTime: Long? = null,
    val duration: Long = 0, // in milliseconds
    val wordsStudied: Int = 0,
    val quizzesTaken: Int = 0,
    val experienceGained: Int = 0
)
