package com.nghia.applen.model

import kotlinx.serialization.Serializable

/**
 * Grammar lesson model
 */
@Serializable
data class Grammar(
    val id: String,
    val title: String,
    val description: String,
    val level: GrammarLevel,
    val category: String, // Tenses, Articles, Conditionals, etc.
    val content: String, // HTML or Markdown content
    val examples: List<GrammarExample>,
    val exercises: List<Exercise>,
    val isCompleted: Boolean = false
)

@Serializable
data class GrammarExample(
    val sentence: String,
    val translation: String? = null,
    val explanation: String? = null
)

@Serializable
data class Exercise(
    val id: String,
    val question: String,
    val options: List<String>,
    val correctAnswer: Int, // index of correct option
    val explanation: String
)

@Serializable
enum class GrammarLevel {
    BASIC,
    INTERMEDIATE,
    ADVANCED
}
