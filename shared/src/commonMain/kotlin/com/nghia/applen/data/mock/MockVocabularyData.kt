package com.nghia.applen.data.mock

import com.nghia.applen.model.Vocabulary

object MockVocabularyData {
    
    fun getSampleVocabulary(): List<Vocabulary> = listOf(
        Vocabulary(
            id = "1",
            word = "achievement",
            definition = "Something accomplished successfully, especially through effort or skill",
            level = "B2",
            category = "Business",
            example = "Graduating from university was a great achievement.",
            pronunciation = "/əˈtʃiːvmənt/",
            audioUrl = null,
            isFavorite = false
        ),
        Vocabulary(
            id = "2",
            word = "collaborate",
            definition = "To work together with others on a project or task",
            level = "C1",
            category = "Business",
            example = "Scientists from different countries collaborate on research projects.",
            pronunciation = "/kəˈlæbəreɪt/",
            audioUrl = null,
            isFavorite = true
        ),
        Vocabulary(
            id = "3",
            word = "efficient",
            definition = "Achieving maximum productivity with minimum wasted effort or expense",
            level = "B2",
            category = "Business",
            example = "The new system is much more efficient than the old one.",
            pronunciation = "/ɪˈfɪʃənt/",
            audioUrl = null,
            isFavorite = false
        ),
        Vocabulary(
            id = "4",
            word = "itinerary",
            definition = "A planned route or journey, especially a detailed plan",
            level = "C1",
            category = "Travel",
            example = "Please send me your travel itinerary so I know when you'll arrive.",
            pronunciation = "/aɪˈtɪnəreri/",
            audioUrl = null,
            isFavorite = false
        ),
        Vocabulary(
            id = "5",
            word = "accommodation",
            definition = "A place to stay or live; lodging",
            level = "B2",
            category = "Travel",
            example = "We need to book accommodation for our trip to Paris.",
            pronunciation = "/əˌkɒməˈdeɪʃən/",
            audioUrl = null,
            isFavorite = true
        ),
        Vocabulary(
            id = "6",
            word = "hypothesis",
            definition = "A proposed explanation made as a starting point for further investigation",
            level = "C2",
            category = "Academic",
            example = "The scientist tested her hypothesis through careful experimentation.",
            pronunciation = "/haɪˈpɒθəsɪs/",
            audioUrl = null,
            isFavorite = false
        ),
        Vocabulary(
            id = "7",
            word = "analyze",
            definition = "To examine something in detail to understand or explain it",
            level = "B2",
            category = "Academic",
            example = "We need to analyze the data carefully before drawing conclusions.",
            pronunciation = "/ˈænəlaɪz/",
            audioUrl = null,
            isFavorite = false
        ),
        Vocabulary(
            id = "8",
            word = "significant",
            definition = "Important, notable, or having a meaning or effect",
            level = "B2",
            category = "Academic",
            example = "There was a significant improvement in sales this quarter.",
            pronunciation = "/sɪɡˈnɪfɪkənt/",
            audioUrl = null,
            isFavorite = true
        ),
        Vocabulary(
            id = "9",
            word = "appreciate",
            definition = "To recognize the value of something or to be grateful for it",
            level = "A2",
            category = "General",
            example = "I really appreciate your help with this project.",
            pronunciation = "/əˈpriːʃieɪt/",
            audioUrl = null,
            isFavorite = false
        ),
        Vocabulary(
            id = "10",
            word = "opportunity",
            definition = "A chance for advancement, progress, or benefit",
            level = "A2",
            category = "General",
            example = "This job offers great opportunities for professional growth.",
            pronunciation = "/ˌɒpəˈtjuːnəti/",
            audioUrl = null,
            isFavorite = false
        )
    )
    
    fun getWordOfTheDay(): Vocabulary {
        val words = getSampleVocabulary()
        val dayOfYear = (System.currentTimeMillis() / (24 * 60 * 60 * 1000)).toInt()
        return words[dayOfYear % words.size]
    }
}
