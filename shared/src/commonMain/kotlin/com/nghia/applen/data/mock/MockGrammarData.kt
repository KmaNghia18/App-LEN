package com.nghia.applen.data.mock

import com.nghia.applen.model.*

object MockGrammarData {
    
    fun getSampleGrammar(): List<Grammar> = listOf(
        Grammar(
            id = "g1",
            title = "Present Simple Tense",
            description = "Learn how to use present simple tense for habits, routines, and general truths",
            level = GrammarLevel.BASIC,
            category = "Tenses",
            content = """
                # Present Simple Tense
                
                ## When to Use
                - Habits and routines
                - General truths and facts
                - Scheduled events
                
                ## Structure
                **Positive**: Subject + base verb + (s/es for 3rd person)
                - I work every day
                - She works at a bank
                
                **Negative**: Subject + do/does + not + base verb
                - I don't work on Sundays
                - He doesn't like coffee
                
                **Question**: Do/Does + subject + base verb?
                - Do you speak English?
                - Does she live here?
            """.trimIndent(),
            examples = listOf(
                GrammarExample(
                    sentence = "The sun rises in the east.",
                    translation = "Mặt trời mọc ở phía đông.",
                    explanation = "General truth"
                ),
                GrammarExample(
                    sentence = "I wake up at 7 AM every day.",
                    translation = "Tôi thức dậy lúc 7 giờ sáng mỗi ngày.",
                    explanation = "Daily routine"
                )
            ),
            exercises = listOf(
                Exercise(
                    id = "ex1",
                    question = "She ___ to work by bus every day.",
                    options = listOf("go", "goes", "going", "gone"),
                    correctAnswer = 1,
                    explanation = "Use 'goes' (base verb + s) for third person singular in present simple."
                ),
                Exercise(
                    id = "ex2",
                    question = "___ you speak French?",
                    options = listOf("Do", "Does", "Are", "Is"),
                    correctAnswer = 0,
                    explanation = "Use 'Do' for questions with 'you' in present simple."
                )
            ),
            isCompleted = false
        ),
        
        Grammar(
            id = "g2",
            title = "Articles: A, An, The",
            description = "Master the usage of definite and indefinite articles in English",
            level = GrammarLevel.BASIC,
            category = "Articles",
            content = """
                # Articles: A, An, The
                
                ## A vs An (Indefinite Articles)
                Use **a** before consonant sounds:
                - a book, a car, a university
                
                Use **an** before vowel sounds:
                - an apple, an hour, an MBA
                
                ## The (Definite Article)
                Use **the** when:
                - Specific thing known
                - Second mention
                - Unique things (the sun)
                - Superlatives (the best)
            """.trimIndent(),
            examples = listOf(
                GrammarExample(
                    sentence = "I saw a cat. The cat was sleeping.",
                    translation = "Tôi nhìn thấy một con mèo. Con mèo đang ngủ.",
                    explanation = "First mention: a, second mention: the"
                ),
                GrammarExample(
                    sentence = "She is the tallest girl in class.",
                    translation = "Cô ấy là cô gái cao nhất trong lớp.",
                    explanation = "Superlative uses 'the'"
                )
            ),
            exercises = listOf(
                Exercise(
                    id = "ex3",
                    question = "She is ___ engineer at Microsoft.",
                    options = listOf("a", "an", "the", "no article"),
                    correctAnswer = 1,
                    explanation = "'Engineer' starts with a vowel sound, so we use 'an'."
                ),
                Exercise(
                    id = "ex4",
                    question = "I need to buy ___ new phone.",
                    options = listOf("a", "an", "the", "no article"),
                    correctAnswer = 0,
                    explanation = "First mention of something uses 'a' or 'an'."
                )
            ),
            isCompleted = false
        ),
        
        Grammar(
            id = "g3",
            title = "Conditional Sentences",
            description = "Learn Type 1 and Type 2 conditionals for real and hypothetical situations",
            level = GrammarLevel.INTERMEDIATE,
            category = "Conditionals",
            content = """
                # Conditional Sentences
                
                ## Type 1: Real/Possible Condition
                **Structure**: If + present simple, will + base verb
                **Use**: Real possibilities in the future
                
                **Examples**:
                - If it rains tomorrow, I will stay home.
                - If you study hard, you will pass the exam.
                
                ## Type 2: Unreal/Hypothetical
                **Structure**: If + past simple, would + base verb
                **Use**: Imaginary situations
                
                **Examples**:
                - If I had a car, I would drive to work.
                - If I were you, I would accept the offer.
            """.trimIndent(),
            examples = listOf(
                GrammarExample(
                    sentence = "If it rains, I will bring an umbrella.",
                    translation = "Nếu trời mưa, tôi sẽ mang ô.",
                    explanation = "Type 1: Real possibility"
                ),
                GrammarExample(
                    sentence = "If I were rich, I would travel the world.",
                    translation = "Nếu tôi giàu, tôi sẽ đi du lịch vòng quanh thế giới.",
                    explanation = "Type 2: Hypothetical situation"
                )
            ),
            exercises = listOf(
                Exercise(
                    id = "ex5",
                    question = "If I ___ more time, I would learn Spanish.",
                    options = listOf("have", "had", "will have", "would have"),
                    correctAnswer = 1,
                    explanation = "Type 2 conditional uses past simple in the if-clause."
                ),
                Exercise(
                    id = "ex6",
                    question = "If she ___ me, I will answer.",
                    options = listOf("call", "calls", "will call", "called"),
                    correctAnswer = 1,
                    explanation = "Type 1 conditional uses present simple in the if-clause."
                )
            ),
            isCompleted = false
        ),
        
        Grammar(
            id = "g4",
            title = "Passive Voice",
            description = "Understand when and how to use passive voice in English",
            level = GrammarLevel.ADVANCED,
            category = "Voice",
            content = """
                # Passive Voice
                
                ## When to Use
                - Focus on the action, not the doer
                - Doer is unknown or unimportant
                - Formal writing
                
                ## Structure
                **Active**: Subject + Verb + Object
                **Passive**: Object + be + past participle
                
                ## Examples
                Active: Shakespeare wrote Hamlet.
                Passive: Hamlet was written by Shakespeare.
                
                Active: Someone stole my bike.
                Passive: My bike was stolen.
            """.trimIndent(),
            examples = listOf(
                GrammarExample(
                    sentence = "The Mona Lisa was painted by Leonardo da Vinci.",
                    translation = "Bức Mona Lisa được vẽ bởi Leonardo da Vinci.",
                    explanation = "Passive voice - past simple"
                ),
                GrammarExample(
                    sentence = "English is spoken in many countries.",
                    translation = "Tiếng Anh được nói ở nhiều quốc gia.",
                    explanation = "Passive voice - present simple"
                )
            ),
            exercises = listOf(
                Exercise(
                    id = "ex7",
                    question = "The Mona Lisa ___ by Leonardo da Vinci.",
                    options = listOf("painted", "was painted", "is painted", "has painted"),
                    correctAnswer = 1,
                    explanation = "Past simple passive: was/were + past participle."
                ),
                Exercise(
                    id = "ex8",
                    question = "English ___ in many countries.",
                    options = listOf("speaks", "is spoken", "was spoken", "has spoken"),
                    correctAnswer = 1,
                    explanation = "Present simple passive: am/is/are + past participle."
                )
            ),
            isCompleted = false
        ),
        
        Grammar(
            id = "g5",
            title = "Modal Verbs",
            description = "Master can, could, may, and might for ability, permission, and possibility",
            level = GrammarLevel.INTERMEDIATE,
            category = "Modals",
            content = """
                # Modal Verbs
                
                ## Can
                - Ability: I can swim.
                - Permission: Can I use your phone?
                
                ## Could
                - Past ability: I could run fast.
                - Polite request: Could you help me?
                
                ## May
                - Permission (formal): May I come in?
                - Possibility: She may be late.
                
                ## Might
                - Lower possibility: I might go.
            """.trimIndent(),
            examples = listOf(
                GrammarExample(
                    sentence = "I can speak three languages.",
                    translation = "Tôi có thể nói ba thứ tiếng.",
                    explanation = "Can = ability"
                ),
                GrammarExample(
                    sentence = "It might rain tomorrow.",
                    translation = "Trời có thể mưa ngày mai.",
                    explanation = "Might = lower possibility"
                )
            ),
            exercises = listOf(
                Exercise(
                    id = "ex9",
                    question = "___ you speak Japanese?",
                    options = listOf("Can", "Could", "May", "Might"),
                    correctAnswer = 0,
                    explanation = "Use 'Can' to ask about present ability."
                ),
                Exercise(
                    id = "ex10",
                    question = "It ___ snow tomorrow, but I'm not sure.",
                    options = listOf("can", "could", "may", "might"),
                    correctAnswer = 3,
                    explanation = "'Might' expresses lower possibility/uncertainty."
                )
            ),
            isCompleted = false
        )
    )
    
    fun getCategoryCounts(): Map<String, Int> {
        return getSampleGrammar()
            .groupBy { it.category }
            .mapValues { it.value.size }
    }
    
    fun getCompletedCount(): Int {
        return getSampleGrammar().count { it.isCompleted }
    }
}
