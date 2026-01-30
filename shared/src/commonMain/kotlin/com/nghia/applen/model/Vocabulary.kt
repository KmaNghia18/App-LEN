package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * Vocabulary word model
 */
@Serializable
data class Vocabulary(
    val id: String,
    val word: String,
    val phonetic: String,
    val audioUrl: String? = null,
    val partOfSpeech: String, // noun, verb, adjective, etc.
    val definitions: List<Definition>,
    val examples: List<String>,
    val synonyms: List<String> = emptyList(),
    val antonyms: List<String> = emptyList(),
    val level: VocabularyLevel,
    val topic: String, // Business, Travel, Academic, etc.
    val isFavorite: Boolean = false,
    val masteryLevel: Int = 0, // 0-5, for spaced repetition
    val lastReviewedAt: Long? = null,
    val nextReviewAt: Long? = null
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
