package com.nghia.applen.data.mock

import com.nghia.applen.model.*

object MockQuizData {
    
    fun getSampleQuizzes(): List<Quiz> {
        val toeicQuiz = createTOEICReadingQuiz()
        val ieltsQuiz = createIELTSReadingQuiz()
        val grammarQuiz = createGrammarQuiz()
        val vocabularyQuiz = createVocabularyQuiz()
        
        return listOf(toeicQuiz, ieltsQuiz, grammarQuiz, vocabularyQuiz)
    }
    
    private fun createTOEICReadingQuiz(): Quiz {
        val quizId = "quiz_toeic_reading"
        val questions = listOf(
            createQuestion(quizId, 1, "The company's annual revenue has ___ significantly this year.", 
                listOf("increase", "increased", "increasing", "to increase"), 1,
                "Present perfect tense requires 'has + past participle', so 'increased' is correct."),
            createQuestion(quizId, 2, "All employees must submit their timesheets ___ Friday.",
                listOf("by", "until", "at", "on"), 0,
                "'By' indicates a deadline - something must be done before or at that time."),
            createQuestion(quizId, 3, "The manager was pleased ___ the team's performance.",
                listOf("with", "at", "for", "about"), 0,
                "The correct collocation is 'pleased with something/someone'."),
            createQuestion(quizId, 4, "Our office is located ___ the third floor.",
                listOf("in", "at", "on", "by"), 2,
                "Use 'on' for floors in a building."),
            createQuestion(quizId, 5, "The meeting has been ___ until next week.",
                listOf("postponed", "delayed", "cancelled", "rescheduled"), 0,
                "'Postponed' means moved to a later time."),
            createQuestion(quizId, 6, "Please ___ me know if you have any questions.",
                listOf("let", "make", "have", "get"), 0,
                "The correct phrase is 'let someone know'."),
            createQuestion(quizId, 7, "The product launch was a great ___.",
                listOf("success", "succeed", "successful", "successfully"), 0,
                "A noun is needed after the article 'a'. 'Success' is the noun form."),
            createQuestion(quizId, 8, "She is responsible ___ managing the project.",
                listOf("for", "to", "with", "of"), 0,
                "The correct collocation is 'responsible for (doing) something'."),
            createQuestion(quizId, 9, "The report must be submitted ___ the end of the month.",
                listOf("by", "until", "in", "at"), 0,
                "'By' is used for deadlines, meaning 'no later than'."),
            createQuestion(quizId, 10, "We are looking forward to ___ you at the conference.",
                listOf("seeing", "see", "saw", "seen"), 0,
                "'Look forward to' is followed by a gerund (verb + -ing).")
        )
        
        return Quiz(
            id = quizId,
            title = "TOEIC Reading Practice - Part 5",
            description = "Incomplete Sentences - 10 questions. Choose the best word or phrase to complete each sentence.",
            type = QuizType.TOEIC,
            section = QuizSection.READING,
            duration = 10,
            questions = questions,
            passingScore = 70,
            totalPoints = 10
        )
    }
    
    private fun createIELTSReadingQuiz(): Quiz {
        val quizId = "quiz_ielts_reading"
        val questions = listOf(
            createQuestion(quizId, 1, "According to the passage, what is the main cause of climate change?",
                listOf("Natural weather patterns", "Human activities and greenhouse gas emissions", 
                       "Solar radiation changes", "Ocean current variations"), 1,
                "The passage clearly states that human activities are the primary driver."),
            createQuestion(quizId, 2, "The author suggests that renewable energy is ___.",
                listOf("too expensive to implement", "a viable solution to reduce emissions",
                       "not effective enough", "only suitable for certain countries"), 1,
                "The passage presents renewable energy as a practical solution."),
            createQuestion(quizId, 3, "What does the term 'carbon footprint' refer to?",
                listOf("The weight of carbon in the atmosphere", 
                       "The total greenhouse gas emissions caused by an individual or organization",
                       "The physical space occupied by carbon", "The cost of carbon removal"), 1,
                "Carbon footprint is defined as the total greenhouse gas emissions."),
            createQuestion(quizId, 4, "According to the text, which sector contributes most to global emissions?",
                listOf("Transportation", "Agriculture", "Energy production", "Manufacturing"), 2,
                "The passage identifies energy production as the largest contributor."),
            createQuestion(quizId, 5, "The passage implies that individual actions ___.",
                listOf("are insignificant compared to corporate actions", 
                       "can collectively make a significant impact",
                       "are the only solution needed", "should not be encouraged"), 1,
                "The text suggests that individual actions, when combined, are meaningful."),
            createQuestion(quizId, 6, "What is the author's overall tone in this passage?",
                listOf("Pessimistic", "Neutral", "Optimistic but urgent", "Dismissive"), 2,
                "The author presents solutions while stressing the urgency of action.")
        )
        
        return Quiz(
            id = quizId,
            title = "IELTS Reading Comprehension",
            description = "Reading comprehension test with multiple choice questions. Test your understanding of academic texts.",
            type = QuizType.IELTS,
            section = QuizSection.READING,
            duration = 15,
            questions = questions,
            passingScore = 75,
            totalPoints = 6
        )
    }
    
    private fun createGrammarQuiz(): Quiz {
        val quizId = "quiz_grammar"
        val questions = listOf(
            createQuestion(quizId, 1, "I need ___ umbrella because it's raining.",
                listOf("a", "an", "the", "no article"), 1,
                "'Umbrella' starts with a vowel sound, so we use 'an'."),
            createQuestion(quizId, 2, "She ___ to the gym every morning.",
                listOf("go", "goes", "going", "gone"), 1,
                "Present simple with third person singular requires '-s'."),
            createQuestion(quizId, 3, "If I ___ rich, I would travel the world.",
                listOf("am", "was", "were", "will be"), 2,
                "Type 2 conditional uses 'were' for all persons after 'if'."),
            createQuestion(quizId, 4, "The book ___ by millions of people.",
                listOf("reads", "is read", "was read", "has read"), 1,
                "Present simple passive: is/are + past participle."),
            createQuestion(quizId, 5, "___ you help me with this problem?",
                listOf("Can", "May", "Might", "Should"), 0,
                "'Can' is commonly used to ask for help or request something."),
            createQuestion(quizId, 6, "This is ___ interesting book I've ever read.",
                listOf("more", "most", "the most", "the more"), 2,
                "Superlative form: the + most/adjective-est."),
            createQuestion(quizId, 7, "She has been working here ___ 2010.",
                listOf("since", "for", "from", "in"), 0,
                "'Since' is used with a specific point in time (year, date)."),
            createQuestion(quizId, 8, "If it rains tomorrow, we ___ stay home.",
                listOf("will", "would", "can", "could"), 0,
                "Type 1 conditional: if + present, will + base verb.")
        )
        
        return Quiz(
            id = quizId,
            title = "Grammar Quick Test",
            description = "Mixed grammar topics - Articles, Tenses, and Conditionals",
            type = QuizType.PRACTICE,
            section = QuizSection.READING,
            duration = 8,
            questions = questions,
            passingScore = 70,
            totalPoints = 8
        )
    }
    
    private fun createVocabularyQuiz(): Quiz {
        val quizId = "quiz_vocabulary"
        val questions = listOf(
            createQuestion(quizId, 1, "What does 'collaborate' mean?",
                listOf("To compete", "To work together", "To separate", "To argue"), 1,
                "'Collaborate' means to work jointly with others."),
            createQuestion(quizId, 2, "Choose the synonym for 'efficient':",
                listOf("Wasteful", "Productive", "Slow", "Careless"), 1,
                "'Efficient' and 'productive' both mean achieving maximum output."),
            createQuestion(quizId, 3, "An 'itinerary' is a ___.",
                listOf("Type of food", "Travel plan", "Document", "Vehicle"), 1,
                "An itinerary is a planned route or journey."),
            createQuestion(quizId, 4, "Choose the antonym for 'significant':",
                listOf("Important", "Notable", "Trivial", "Meaningful"), 2,
                "'Trivial' means unimportant, the opposite of 'significant'."),
            createQuestion(quizId, 5, "What does 'accommodate' mean in a business context?",
                listOf("To refuse", "To provide what is needed or wanted", 
                       "To charge extra", "To delay"), 1,
                "'Accommodate' means to fit in with someone's wishes or needs."),
            createQuestion(quizId, 6, "A 'hypothesis' is a ___.",
                listOf("Proven fact", "Wild guess", "Proposed explanation", "Final answer"), 2,
                "A hypothesis is a proposed explanation that needs testing."),
            createQuestion(quizId, 7, "What does 'analyze' mean?",
                listOf("To ignore", "To examine in detail", "To memorize", "To destroy"), 1,
                "'Analyze' means to examine something methodically and in detail."),
            createQuestion(quizId, 8, "An 'opportunity' is a ___.",
                listOf("Problem", "Chance", "Obstacle", "Threat"), 1,
                "An opportunity is a favorable chance or occasion."),
            createQuestion(quizId, 9, "Choose the synonym for 'achievement':",
                listOf("Failure", "Accomplishment", "Attempt", "Beginning"), 1,
                "Both 'achievement' and 'accomplishment' mean something successfully completed."),
            createQuestion(quizId, 10, "What does 'resilient' mean?",
                listOf("Able to recover quickly", "Very fragile", 
                       "Permanently damaged", "Unchangeable"), 0,
                "'Resilient' means able to bounce back from difficulties.")
        )
        
        return Quiz(
            id = quizId,
            title = "Business Vocabulary Quiz",
            description = "Test your knowledge of business English vocabulary",
            type = QuizType.PRACTICE,
            section = QuizSection.READING,
            duration = 12,
            questions = questions,
            passingScore = 80,
            totalPoints = 10
        )
    }
    
    private fun createQuestion(
        quizId: String,
        number: Int,
        text: String,
        options: List<String>,
        correctIndex: Int,
        explanation: String
    ) = Question(
        id = "${quizId}_q$number",
        quizId = quizId,
        questionText = text,
        questionNumber = number,
        options = options,
        correctAnswer = correctIndex,
        explanation = explanation,
        points = 1
    )
}
