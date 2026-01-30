package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * User model
 */
@Serializable
data class User(
    val id: String,
    val email: String,
    val name: String,
    val avatarUrl: String? = null,
    val targetExam: ExamTarget = ExamTarget.TOEIC,
    val targetScore: Int = 700,
    val studyGoal: String? = null, // e.g., "Pass TOEIC 900 by June"
    val createdAt: Long,
    val lastActiveAt: Long
)

@Serializable
enum class ExamTarget {
    TOEIC,
    IELTS,
    GENERAL
}
