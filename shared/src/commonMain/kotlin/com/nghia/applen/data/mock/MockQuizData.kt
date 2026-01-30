package com.nghia.applen.data.mock

import com.nghia.applen.model.*

object MockQuizData {
    
    fun getSampleQuizzes(): List<Quiz> = listOf(
        Quiz(
            id = "quiz1",
            title = "TOEIC Reading Practice - Part 5",
            description = "Incomplete Sentences - 10 questions. Choose the best word or phrase to complete each sentence.",
            type = QuizType.TOEIC,
            level = QuizLevel.INTERMEDIATE,
            totalQuestions = 10,
            timeLimitMinutes = 10,
            passingScore = 70,
            questions = getTOEICPart5Questions()
        ),
        
        Quiz(
            id = "quiz2",
            title = "IELTS Reading Comprehension",
            description = "Reading comprehension test with multiple choice questions. Test your understanding of academic texts.",
            type = QuizType.IELTS,
            level = QuizLevel.ADVANCED,
            totalQuestions = 8,
            timeLimitMinutes = 15,
            passingScore = 75,
            questions = getIELTSReadingQuestions()
        ),
        
        Quiz(
            id = "quiz3",
            title = "Grammar Quick Test",
            description = "Mixed grammar topics - Articles, Tenses, and Conditionals",
            type = QuizType.GRAMMAR,
            level = QuizLevel.INTERMEDIATE,
            totalQuestions = 12,
            timeLimitMinutes = 8,
            passingScore = 70,
            questions = getGrammarQuestions()
        ),
        
        Quiz(
            id = "quiz4",
            title = "Business Vocabulary Quiz",
            description = "Test your knowledge of business English vocabulary",
            type = QuizType.VOCABULARY,
            level = QuizLevel.ADVANCED,
            totalQuestions = 15,
            timeLimitMinutes = 12,
            passingScore = 80,
            questions = getVocabularyQuestions()
        )
    )
    
    private fun getTOEICPart5Questions() = listOf(
        Question(
            id = "q1_1",
            questionNumber = 1,
            questionText = "The company's annual revenue has ___ significantly this year.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("increase", "increased", "increasing", "to increase"),
            correctAnswer = "increased",
            explanation = "Present perfect tense requires 'has + past participle', so 'increased' is correct.",
            points = 1
        ),
        Question(
            id = "q1_2",
            questionNumber = 2,
            questionText = "All employees must submit their timesheets ___ Friday.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("by", "until", "at", "on"),
            correctAnswer = "by",
            explanation = "'By' indicates a deadline - something must be done before or at that time.",
            points = 1
        ),
        Question(
            id = "q1_3",
            questionNumber = 3,
            questionText = "The manager was pleased ___ the team's performance.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("with", "at", "for", "about"),
            correctAnswer = "with",
            explanation = "The correct collocation is 'pleased with something/someone'.",
            points = 1
        ),
        Question(
            id = "q1_4",
            questionNumber = 4,
            questionText = "Our office is located ___ the third floor.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("in", "at", "on", "by"),
            correctAnswer = "on",
            explanation = "Use 'on' for floors in a building.",
            points = 1
        ),
        Question(
            id = "q1_5",
            questionNumber = 5,
            questionText = "The meeting has been ___ until next week.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("postponed", "delayed", "cancelled", "rescheduled"),
            correctAnswer = "postponed",
            explanation = "'Postponed' means moved to a later time, which fits the context.",
            points = 1
        ),
        Question(
            id = "q1_6",
            questionNumber = 6,
            questionText = "Please ___ me know if you have any questions.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("let", "make", "have", "get"),
            correctAnswer = "let",
            explanation = "The correct phrase is 'let someone know'.",
            points = 1
        ),
        Question(
            id = "q1_7",
            questionNumber = 7,
            questionText = "The product launch was a great ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("success", "succeed", "successful", "successfully"),
            correctAnswer = "success",
            explanation = "A noun is needed after the article 'a'. 'Success' is the noun form.",
            points = 1
        ),
        Question(
            id = "q1_8",
            questionNumber = 8,
            questionText = "She is responsible ___ managing the project.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("for", "to", "with", "of"),
            correctAnswer = "for",
            explanation = "The correct collocation is 'responsible for (doing) something'.",
            points = 1
        ),
        Question(
            id = "q1_9",
            questionNumber = 9,
            questionText = "The report must be submitted ___ the end of the month.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("by", "until", "in", "at"),
            correctAnswer = "by",
            explanation = "'By' is used for deadlines, meaning 'no later than'.",
            points = 1
        ),
        Question(
            id = "q1_10",
            questionNumber = 10,
            questionText = "We are looking forward to ___ you at the conference.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("seeing", "see", "saw", "seen"),
            correctAnswer = "seeing",
            explanation = "'Look forward to' is followed by a gerund (verb + -ing).",
            points = 1
        )
    )
    
    private fun getIELTSReadingQuestions() = listOf(
        Question(
            id = "q2_1",
            questionNumber = 1,
            questionText = "According to the passage, what is the main cause of climate change?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Natural weather patterns",
                "Human activities and greenhouse gas emissions",
                "Solar radiation changes",
                "Ocean current variations"
            ),
            correctAnswer = "Human activities and greenhouse gas emissions",
            explanation = "The passage clearly states that human activities are the primary driver.",
            points = 1
        ),
        Question(
            id = "q2_2",
            questionNumber = 2,
            questionText = "The author suggests that renewable energy is ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "too expensive to implement",
                "a viable solution to reduce emissions",
                "not effective enough",
                "only suitable for certain countries"
            ),
            correctAnswer = "a viable solution to reduce emissions",
            explanation = "The passage presents renewable energy as a practical solution.",
            points = 1
        ),
        Question(
            id = "q2_3",
            questionNumber = 3,
            questionText = "True or False: The passage mentions that electric vehicles produce zero emissions.",
            questionType = QuestionType.TRUE_FALSE,
            options = listOf("True", "False"),
            correctAnswer = "False",
            explanation = "The passage clarifies that while EVs have no tailpipe emissions, electricity production may produce emissions.",
            points = 1
        ),
        Question(
            id = "q2_4",
            questionNumber = 4,
            questionText = "What does the term 'carbon footprint' refer to?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "The weight of carbon in the atmosphere",
                "The total greenhouse gas emissions caused by an individual or organization",
                "The physical space occupied by carbon",
                "The cost of carbon removal"
            ),
            correctAnswer = "The total greenhouse gas emissions caused by an individual or organization",
            explanation = "Carbon footprint is defined as the total greenhouse gas emissions.",
            points = 1
        ),
        Question(
            id = "q2_5",
            questionNumber = 5,
            questionText = "According to the text, which sector contributes most to global emissions?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Transportation", "Agriculture", "Energy production", "Manufacturing"),
            correctAnswer = "Energy production",
            explanation = "The passage identifies energy production as the largest contributor.",
            points = 1
        ),
        Question(
            id = "q2_6",
            questionNumber = 6,
            questionText = "The passage implies that individual actions ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "are insignificant compared to corporate actions",
                "can collectively make a significant impact",
                "are the only solution needed",
                "should not be encouraged"
            ),
            correctAnswer = "can collectively make a significant impact",
            explanation = "The text suggests that individual actions, when combined, are meaningful.",
            points = 1
        ),
        Question(
            id = "q2_7",
            questionNumber = 7,
            questionText = "True or False: The author believes technology alone can solve climate change.",
            questionType = QuestionType.TRUE_FALSE,
            options = listOf("True", "False"),
            correctAnswer = "False",
            explanation = "The passage emphasizes that both technology and behavioral changes are needed.",
            points = 1
        ),
        Question(
            id = "q2_8",
            questionNumber = 8,
            questionText = "What is the author's overall tone in this passage?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Pessimistic", "Neutral", "Optimistic but urgent", "Dismissive"),
            correctAnswer = "Optimistic but urgent",
            explanation = "The author presents solutions while stressing the urgency of action.",
            points = 1
        )
    )
    
    private fun getGrammarQuestions() = listOf(
        Question(
            id = "q3_1",
            questionNumber = 1,
            questionText = "I need ___ umbrella because it's raining.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("a", "an", "the", "no article"),
            correctAnswer = "an",
            explanation = "'Umbrella' starts with a vowel sound, so we use 'an'.",
            points = 1
        ),
        Question(
            id = "q3_2",
            questionNumber = 2,
            questionText = "She ___ to the gym every morning.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("go", "goes", "going", "gone"),
            correctAnswer = "goes",
            explanation = "Present simple with third person singular requires '-s'.",
            points = 1
        ),
        Question(
            id = "q3_3",
            questionNumber = 3,
            questionText = "If I ___ rich, I would travel the world.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("am", "was", "were", "will be"),
            correctAnswer = "were",
            explanation = "Type 2 conditional uses 'were' for all persons after 'if'.",
            points = 1
        ),
        Question(
            id = "q3_4",
            questionNumber = 4,
            questionText = "The book ___ by millions of people.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("reads", "is read", "was read", "has read"),
            correctAnswer = "is read",
            explanation = "Present simple passive: is/are + past participle.",
            points = 1
        ),
        Question(
            id = "q3_5",
            questionNumber = 5,
            questionText = "___ you help me with this problem?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Can", "May", "Might", "Should"),
            correctAnswer = "Can",
            explanation = "'Can' is commonly used to ask for help or request something.",
            points = 1
        ),
        Question(
            id = "q3_6",
            questionNumber = 6,
            questionText = "I have ___ finished my homework.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("yet", "already", "still", "just"),
            correctAnswer = "already",
            explanation = "'Already' is used in positive statements to show something is completed.",
            points = 1
        ),
        Question(
            id = "q3_7",
            questionNumber = 7,
            questionText = "This is ___ interesting book I've ever read.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("more", "most", "the most", "the more"),
            correctAnswer = "the most",
            explanation = "Superlative form: the + most/adjective-est.",
            points = 1
        ),
        Question(
            id = "q3_8",
            questionNumber = 8,
            questionText = "She has been working here ___ 2010.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("since", "for", "from", "in"),
            correctAnswer = "since",
            explanation = "'Since' is used with a specific point in time (year, date).",
            points = 1
        ),
        Question(
            id = "q3_9",
            questionNumber = 9,
            questionText = "I'm looking forward ___ the concert.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("to", "for", "at", "in"),
            correctAnswer = "to",
            explanation = "The phrasal verb is 'look forward to'.",
            points = 1
        ),
        Question(
            id = "q3_10",
            questionNumber = 10,
            questionText = "Neither John nor his friends ___ coming to the party.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("is", "are", "was", "were"),
            correctAnswer = "are",
            explanation = "With 'neither...nor', the verb agrees with the nearest subject ('friends').",
            points = 1
        ),
        Question(
            id = "q3_11",
            questionNumber = 11,
            questionText = "If it rains tomorrow, we ___ stay home.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("will", "would", "can", "could"),
            correctAnswer = "will",
            explanation = "Type 1 conditional: if + present, will + base verb.",
            points = 1
        ),
        Question(
            id = "q3_12",
            questionNumber = 12,
            questionText = "The movie was ___ boring that I fell asleep.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("so", "such", "very", "too"),
            correctAnswer = "so",
            explanation = "'So...that' is used to show result or consequence.",
            points = 1
        )
    )
    
    private fun getVocabularyQuestions() = listOf(
        Question(
            id = "q4_1",
            questionNumber = 1,
            questionText = "What does 'collaborate' mean?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("To compete", "To work together", "To separate", "To argue"),
            correctAnswer = "To work together",
            explanation = "'Collaborate' means to work jointly with others.",
            points = 1
        ),
        Question(
            id = "q4_2",
            questionNumber = 2,
            questionText = "Choose the synonym for 'efficient':",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Wasteful", "Productive", "Slow", "Careless"),
            correctAnswer = "Productive",
            explanation = "'Efficient' and 'productive' both mean achieving maximum output.",
            points = 1
        ),
        Question(
            id = "q4_3",
            questionNumber = 3,
            questionText = "An 'itinerary' is a ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Type of food", "Travel plan", "Document", "Vehicle"),
            correctAnswer = "Travel plan",
            explanation = "An itinerary is a planned route or journey.",
            points = 1
        ),
        Question(
            id = "q4_4",
            questionNumber = 4,
            questionText = "Choose the antonym for 'significant':",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Important", "Notable", "Trivial", "Meaningful"),
            correctAnswer = "Trivial",
            explanation = "'Trivial' means unimportant, the opposite of 'significant'.",
            points = 1
        ),
        Question(
            id = "q4_5",
            questionNumber = 5,
            questionText = "What does 'accommodate' mean in a business context?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "To refuse",
                "To provide what is needed or wanted",
                "To charge extra",
                "To delay"
            ),
            correctAnswer = "To provide what is needed or wanted",
            explanation = "'Accommodate' means to fit in with someone's wishes or needs.",
            points = 1
        ),
        Question(
            id = "q4_6",
            questionNumber = 6,
            questionText = "A 'hypothesis' is a ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Proven fact", "Wild guess", "Proposed explanation", "Final answer"),
            correctAnswer = "Proposed explanation",
            explanation = "A hypothesis is a proposed explanation that needs testing.",
            points = 1
        ),
        Question(
            id = "q4_7",
            questionNumber = 7,
            questionText = "Choose the correct usage of 'appreciate':",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "I appreciate for your help.",
                "I appreciate your help.",
                "I appreciate to your help.",
                "I appreciate at your help."
            ),
            correctAnswer = "I appreciate your help.",
            explanation = "'Appreciate' is followed directly by a noun or gerund.",
            points = 1
        ),
        Question(
            id = "q4_8",
            questionNumber = 8,
            questionText = "What does 'analyze' mean?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "To ignore",
                "To examine in detail",
                "To memorize",
                "To destroy"
            ),
            correctAnswer = "To examine in detail",
            explanation = "'Analyze' means to examine something methodically and in detail.",
            points = 1
        ),
        Question(
            id = "q4_9",
            questionNumber = 9,
            questionText = "An 'opportunity' is a ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Problem", "Chance", "Obstacle", "Threat"),
            correctAnswer = "Chance",
            explanation = "An opportunity is a favorable chance or occasion.",
            points = 1
        ),
        Question(
            id = "q4_10",
            questionNumber = 10,
            questionText = "Choose the synonym for 'achievement':",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Failure", "Accomplishment", "Attempt", "Beginning"),
            correctAnswer = "Accomplishment",
            explanation = "Both 'achievement' and 'accomplishment' mean something successfully completed.",
            points = 1
        ),
        Question(
            id = "q4_11",
            questionNumber = 11,
            questionText = "What does 'resilient' mean?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Able to recover quickly",
                "Very fragile",
                "Permanently damaged",
                "Unchangeable"
            ),
            correctAnswer = "Able to recover quickly",
            explanation = "'Resilient' means able to bounce back from difficulties.",
            points = 1
        ),
        Question(
            id = "q4_12",
            questionNumber = 12,
            questionText = "'Comprehensive' means ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Partial", "Complete and thorough", "Simple", "Brief"),
            correctAnswer = "Complete and thorough",
            explanation = "'Comprehensive' means including all or nearly all elements.",
            points = 1
        ),
        Question(
            id = "q4_13",
            questionNumber = 13,
            questionText = "Choose the antonym for 'novice':",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf("Beginner", "Expert", "Student", "Amateur"),
            correctAnswer = "Expert",
            explanation = "A 'novice' is a beginner, the opposite of an 'expert'.",
            points = 1
        ),
        Question(
            id = "q4_14",
            questionNumber = 14,
            questionText = "What does 'mitigate' mean?",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "To make worse",
                "To make less severe",
                "To eliminate completely",
                "To ignore"
            ),
            correctAnswer = "To make less severe",
            explanation = "'Mitigate' means to make something less severe or serious.",
            points = 1
        ),
        Question(
            id = "q4_15",
            questionNumber = 15,
            questionText = "A 'proactive' person is someone who ___.",
            questionType = QuestionType.MULTIPLE_CHOICE,
            options = listOf(
                "Waits for things to happen",
                "Takes initiative and anticipates needs",
                "Reacts after problems occur",
                "Avoids responsibility"
            ),
            correctAnswer = "Takes initiative and anticipates needs",
            explanation = "'Proactive' means taking action in advance to deal with expected difficulties.",
            points = 1
        )
    )
}
