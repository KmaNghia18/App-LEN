package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * Vocabulary word model
 */
@Serializable
data class Vocabulary(
    val id: String,
    val word: String,
    val definition: String,
    val level: String,
    val category: String,
    val example: String,
    val pronunciation: String,
    val audioUrl: String? = null,  // URL for pronunciation audio
    val isFavorite: Boolean = false,
    val lastReviewedAt: Long? = null,
    val easeFactor: Double = 2.5,
    val interval: Int = 0,
    val repetitions: Int = 0
)

@Serializable
data class Definition(
    val meaning: String,
    val example: String? = null
)

@Serializable
enum class VocabularyLevel {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED,
    EXPERT
}
