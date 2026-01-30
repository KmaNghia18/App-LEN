package com.nghia.applen.data.mock

import com.nghia.applen.model.Question
import com.nghia.applen.model.Quiz

object MockListeningData {
    
    fun getSampleListeningTests(): List<Quiz> {
        return listOf(
            createTOEICListeningPart1(),
            createIELTSListeningSection1()
        )
    }
    
    private fun createTOEICListeningPart1(): Quiz {
        val quizId = "listening_toeic_part1"
        
        val questions = listOf(
            Question(
                id = "q1",
                quizId = quizId,
                questionText = "Look at the picture. What is happening?",
                questionNumber = 1,
                options = listOf(
                    "A. The woman is reading a book",
                    "B. The woman is talking on the phone",
                    "C. The woman is typing on a computer",
                    "D. The woman is drinking coffee"
                ),
                correctAnswer = 1,
                audioUrl = "https://example.com/audio/toeic_p1_q1.mp3",
                imageUrl = "https://example.com/images/toeic_p1_q1.jpg",
                explanation = "The audio describes a woman talking on the phone.",
                points = 1
            ),
            Question(
                id = "q2",
                quizId = quizId,
                questionText = "What do you see in the picture?",
                questionNumber = 2,
                options = listOf(
                    "A. People are sitting in a meeting room",
                    "B. People are standing in a hallway",
                    "C. People are eating in a cafeteria",
                    "D. People are working in an office"
                ),
                correctAnswer = 0,
                audioUrl = "https://example.com/audio/toeic_p1_q2.mp3",
                imageUrl = "https://example.com/images/toeic_p1_q2.jpg",
                explanation = "The audio mentions people sitting in a conference room.",
                points = 1
            ),
            Question(
                id = "q3",
                quizId = quizId,
                questionText = "What is the man doing?",
                questionNumber = 3,
                options = listOf(
                    "A. He is cooking food",
                    "B. He is cleaning the floor",
                    "C. He is fixing a car",
                    "D. He is watering plants"
                ),
                correctAnswer = 3,
                audioUrl = "https://example.com/audio/toeic_p1_q3.mp3",
                imageUrl = "https://example.com/images/toeic_p1_q3.jpg",
                explanation = "The audio describes a man watering plants in a garden.",
                points = 1
            ),
            Question(
                id = "q4",
                quizId = quizId,
                questionText = "What are the people doing?",
                questionNumber = 4,
                options = listOf(
                    "A. They are playing sports",
                    "B. They are shopping for groceries",
                    "C. They are boarding a train",
                    "D. They are having a picnic"
                ),
                correctAnswer = 2,
                audioUrl = "https://example.com/audio/toeic_p1_q4.mp3",
                imageUrl = "https://example.com/images/toeic_p1_q4.jpg",
                explanation = "The audio describes people getting on a train at the platform.",
                points = 1
            ),
            Question(
                id = "q5",
                quizId = quizId,
                questionText = "What is shown in the image?",
                questionNumber = 5,
                options = listOf(
                    "A. A busy street intersection",
                    "B. An empty parking lot",
                    "C. A crowded marketplace",
                    "D. A quiet park"
                ),
                correctAnswer = 0,
                audioUrl = "https://example.com/audio/toeic_p1_q5.mp3",
                imageUrl = "https://example.com/images/toeic_p1_q5.jpg",
                explanation = "The audio mentions cars and pedestrians at a busy intersection.",
                points = 1
            )
        )
        
        return Quiz(
            id = quizId,
            title = "TOEIC Listening - Part 1",
            description = "Photo description questions with audio",
            category = "TOEIC",
            level = "Intermediate",
            duration = 5, // 5 minutes
            totalQuestions = 5,
            passingScore = 60,
            questions = questions,
            tags = listOf("listening", "toeic", "part1"),
            hasAudio = true,
            hasImages = true
        )
    }
    
    private fun createIELTSListeningSection1(): Quiz {
        val quizId = "listening_ielts_s1"
        
        val questions = listOf(
            Question(
                id = "q1",
                quizId = quizId,
                questionText = "What is the woman's name?",
                questionNumber = 1,
                options = listOf(
                    "A. Sarah Johnson",
                    "B. Sarah Thompson",
                    "C. Susan Johnson",
                    "D. Susan Thompson"
                ),
                correctAnswer = 1,
                audioUrl = "https://example.com/audio/ielts_s1_q1.mp3",
                explanation = "The woman introduces herself as Sarah Thompson.",
                points = 1
            ),
            Question(
                id = "q2",
                quizId = quizId,
                questionText = "What is her phone number?",
                questionNumber = 2,
                options = listOf(
                    "A. 555-1234",
                    "B. 555-4321",
                    "C. 555-2468",
                    "D. 555-8642"
                ),
                correctAnswer = 2,
                audioUrl = "https://example.com/audio/ielts_s1_q2.mp3",
                explanation = "She mentions her phone number is 555-2468.",
                points = 1
            ),
            Question(
                id = "q3",
                quizId = quizId,
                questionText = "When does she want to schedule the appointment?",
                questionNumber = 3,
                options = listOf(
                    "A. Monday morning",
                    "B. Tuesday afternoon",
                    "C. Wednesday evening",
                    "D. Thursday morning"
                ),
                correctAnswer = 1,
                audioUrl = "https://example.com/audio/ielts_s1_q3.mp3",
                explanation = "She prefers Tuesday afternoon for the appointment.",
                points = 1
            ),
            Question(
                id = "q4",
                quizId = quizId,
                questionText = "What service is she interested in?",
                questionNumber = 4,
                options = listOf(
                    "A. Dental cleaning",
                    "B. Car repair",
                    "C. House cleaning",
                    "D. Language course"
                ),
                correctAnswer = 3,
                audioUrl = "https://example.com/audio/ielts_s1_q4.mp3",
                explanation = "She mentions wanting to enroll in a language course.",
                points = 1
            ),
            Question(
                id = "q5",
                quizId = quizId,
                questionText = "How did she hear about the service?",
                questionNumber = 5,
                options = listOf(
                    "A. From a friend",
                    "B. From a website",
                    "C. From a newspaper ad",
                    "D. From a TV commercial"
                ),
                correctAnswer = 0,
                audioUrl = "https://example.com/audio/ielts_s1_q5.mp3",
                explanation = "She says her friend recommended the service.",
                points = 1
            ),
            Question(
                id = "q6",
                quizId = quizId,
                questionText = "What level is she currently at?",
                questionNumber = 6,
                options = listOf(
                    "A. Beginner",
                    "B. Elementary",
                    "C. Intermediate",
                    "D. Advanced"
                ),
                correctAnswer = 2,
                audioUrl = "https://example.com/audio/ielts_s1_q6.mp3",
                explanation = "She states her current level is intermediate.",
                points = 1
            )
        )
        
        return Quiz(
            id = quizId,
            title = "IELTS Listening - Section 1",
            description = "Conversation about making an appointment",
            category = "IELTS",
            level = "Intermediate",
            duration = 8, // 8 minutes
            totalQuestions = 6,
            passingScore = 70,
            questions = questions,
            tags = listOf("listening", "ielts", "section1"),
            hasAudio = true,
            hasImages = false
        )
    }
}
