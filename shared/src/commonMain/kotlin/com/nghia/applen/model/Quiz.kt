package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * Quiz/Test model for TOEIC/IELTS practice
 */
@Serializable
data class Quiz(
    val id: String,
    val title: String,
    val description: String,
    val type: QuizType,
    val section: QuizSection,
    val duration: Int, // in minutes
    val questions: List<Question>,
    val passingScore: Int = 70,
    val totalPoints: Int
)

@Serializable
data class Question(
    val id: String,
    val type: QuestionType,
    val text: String,
    val passage: String? = null, // For reading comprehension
    val audioUrl: String? = null, // For listening questions
    val imageUrl: String? = null,
    val options: List<String>,
    val correctAnswer: Int, // index of correct option
    val explanation: String? = null,
    val points: Int = 1
)

@Serializable
enum class QuizType {
    TOEIC,
    IELTS,
    PRACTICE,
    FULL_TEST
}

@Serializable
enum class QuizSection {
    READING,
    LISTENING,
    WRITING,
    SPEAKING,
    MIXED
}

@Serializable
enum class QuestionType {
    MULTIPLE_CHOICE,
    FILL_IN_BLANK,
    TRUE_FALSE,
    MATCHING
}

/**
 * User's quiz attempt
 */
@Serializable
data class QuizAttempt(
    val id: String,
    val quizId: String,
    val userId: String,
    val answers: Map<String, Int>, // questionId -> selectedOptionIndex
    val score: Int,
    val totalPoints: Int,
    val startedAt: Long,
    val completedAt: Long,
    val timeTaken: Int // in seconds
)
