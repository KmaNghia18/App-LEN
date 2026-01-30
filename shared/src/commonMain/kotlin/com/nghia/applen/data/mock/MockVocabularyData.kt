package com.nghia.applen.data.mock

import com.nghia.applen.model.*

object MockVocabularyData {
    
    fun getSampleVocabulary(): List<Vocabulary> = listOf(
        // Business vocabulary
        Vocabulary(
            id = "1",
            word = "achievement",
            phonetic = "/əˈtʃiːvmənt/",
            audioUrl = null,
            partOfSpeech = "noun",
            definitions = listOf(
                Definition("Something accomplished successfully", "Graduating from university was a great achievement."),
                Definition("The act of achieving", null)
            ),
            examples = listOf(
                "The team's achievement was celebrated by the entire company.",
                "Her academic achievements are impressive."
            ),
            synonyms = listOf("accomplishment", "success", "attainment"),
            antonyms = listOf("failure", "defeat"),
            level = VocabularyLevel.INTERMEDIATE,
            topic = "Business",
            isFavorite = false,
            masteryLevel = 0
        ),
        
        Vocabulary(
            id = "2",
            word = "collaborate",
            phonetic = "/kəˈlæbəreɪt/",
            audioUrl = null,
            partOfSpeech = "verb",
            definitions = listOf(
                Definition("To work together with others", "Scientists from different countries collaborate on research projects.")
            ),
            examples = listOf(
                "The two companies decided to collaborate on the new product.",
                "We need to collaborate more effectively as a team."
            ),
            synonyms = listOf("cooperate", "team up", "work together"),
            antonyms = listOf("compete", "oppose"),
            level = VocabularyLevel.ADVANCED,
            topic = "Business",
            isFavorite = true,
            masteryLevel = 2
        ),
        
        Vocabulary(
            id = "3",
            word = "efficient",
            phonetic = "/ɪˈfɪʃənt/",
            audioUrl = null,
            partOfSpeech = "adjective",
            definitions = listOf(
                Definition("Achieving maximum productivity with minimum wasted effort", "The new system is much more efficient than the old one.")
            ),
            examples = listOf(
                "She's very efficient at managing her time.",
                "We need to find a more efficient way to solve this problem."
            ),
            synonyms = listOf("effective", "productive", "capable"),
            antonyms = listOf("inefficient", "wasteful"),
            level = VocabularyLevel.INTERMEDIATE,
            topic = "Business",
            isFavorite = false,
            masteryLevel = 1
        ),
        
        // Travel vocabulary
        Vocabulary(
            id = "4",
            word = "itinerary",
            phonetic = "/aɪˈtɪnəreri/",
            audioUrl = null,
            partOfSpeech = "noun",
            definitions = listOf(
                Definition("A planned route or journey", "Please send me your travel itinerary.")
            ),
            examples = listOf(
                "Our itinerary includes visits to five different cities.",
                "I need to finalize my trip itinerary before booking flights."
            ),
            synonyms = listOf("schedule", "plan", "route"),
            antonyms = emptyList(),
            level = VocabularyLevel.ADVANCED,
            topic = "Travel",
            isFavorite = false,
            masteryLevel = 0
        ),
        
        Vocabulary(
            id = "5",
            word = "accommodation",
            phonetic = "/əˌkɒməˈdeɪʃən/",
            audioUrl = null,
            partOfSpeech = "noun",
            definitions = listOf(
                Definition("A place to stay or live", "We need to book accommodation for our trip.")
            ),
            examples = listOf(
                "The hotel provides comfortable accommodation.",
                "Finding affordable accommodation in the city can be challenging."
            ),
            synonyms = listOf("lodging", "housing", "shelter"),
            antonyms = emptyList(),
            level = VocabularyLevel.INTERMEDIATE,
            topic = "Travel",
            isFavorite = true,
            masteryLevel = 3
        ),
        
        // Academic vocabulary
        Vocabulary(
            id = "6",
            word = "hypothesis",
            phonetic = "/haɪˈpɒθəsɪs/",
            audioUrl = null,
            partOfSpeech = "noun",
            definitions = listOf(
                Definition("A proposed explanation made as a starting point for further investigation", "The scientist tested her hypothesis through experiments.")
            ),
            examples = listOf(
                "Our hypothesis was confirmed by the experimental results.",
                "They developed a hypothesis to explain the phenomenon."
            ),
            synonyms = listOf("theory", "assumption", "proposition"),
            antonyms = listOf("fact", "certainty"),
            level = VocabularyLevel.EXPERT,
            topic = "Academic",
            isFavorite = false,
            masteryLevel = 0
        ),
        
        Vocabulary(
            id = "7",
            word = "analyze",
            phonetic = "/ˈænəlaɪz/",
            audioUrl = null,
            partOfSpeech = "verb",
            definitions = listOf(
                Definition("Examine in detail to understand or explain", "We need to analyze the data carefully.")
            ),
            examples = listOf(
                "The researchers analyzed thousands of responses.",
                "Can you analyze this problem and provide a solution?"
            ),
            synonyms = listOf("examine", "study", "investigate"),
            antonyms = listOf("ignore", "overlook"),
            level = VocabularyLevel.INTERMEDIATE,
            topic = "Academic",
            isFavorite = false,
            masteryLevel = 2
        ),
        
        Vocabulary(
            id = "8",
            word = "significant",
            phonetic = "/sɪɡˈnɪfɪkənt/",
            audioUrl = null,
            partOfSpeech = "adjective",
            definitions = listOf(
                Definition("Important or notable", "There was a significant improvement in sales."),
                Definition("Having a meaning", null)
            ),
            examples = listOf(
                "This is a significant development in the field.",
                "The results show a significant difference between groups."
            ),
            synonyms = listOf("important", "notable", "meaningful"),
            antonyms = listOf("insignificant", "trivial"),
            level = VocabularyLevel.INTERMEDIATE,
            topic = "Academic",
            isFavorite = true,
            masteryLevel = 4
        ),
        
        // Beginner words
        Vocabulary(
            id = "9",
            word = "appreciate",
            phonetic = "/əˈpriːʃieɪt/",
            audioUrl = null,
            partOfSpeech = "verb",
            definitions = listOf(
                Definition("To recognize the value of something", "I really appreciate your help."),
                Definition("To increase in value", "The property has appreciated significantly.")
            ),
            examples = listOf(
                "I appreciate your patience.",
                "We appreciate all the hard work you've done."
            ),
            synonyms = listOf("value", "recognize", "be grateful for"),
            antonyms = listOf("depreciate", "undervalue"),
            level = VocabularyLevel.BEGINNER,
            topic = "General",
            isFavorite = false,
            masteryLevel = 5
        ),
        
        Vocabulary(
            id = "10",
            word = "opportunity",
            phonetic = "/ˌɒpəˈtjuːnəti/",
            audioUrl = null,
            partOfSpeech = "noun",
            definitions = listOf(
                Definition("A chance for advancement or progress", "This job offers great opportunities for growth.")
            ),
            examples = listOf(
                "Don't miss this opportunity to learn something new.",
                "The internship provided valuable opportunities for networking."
            ),
            synonyms = listOf("chance", "prospect", "possibility"),
            antonyms = listOf("obstacle", "disadvantage"),
            level = VocabularyLevel.BEGINNER,
            topic = "General",
            isFavorite = false,
            masteryLevel = 0
        )
    )
    
    fun getSampleDueCards(): List<Vocabulary> {
        val allWords = getSampleVocabulary()
        val now = System.currentTimeMillis()
        
        return allWords.take(5).map { word ->
            word.copy(
                lastReviewedAt = now - (2 * 24 * 60 * 60 * 1000), // 2 days ago
                nextReviewAt = now - (1 * 60 * 60 * 1000) // 1 hour ago (due now)
            )
        }
    }
    
    fun getWordOfTheDay(): Vocabulary {
        val words = getSampleVocabulary()
        val dayOfYear = (System.currentTimeMillis() / (24 * 60 * 60 * 1000)).toInt()
        return words[dayOfYear % words.size]
    }
}
