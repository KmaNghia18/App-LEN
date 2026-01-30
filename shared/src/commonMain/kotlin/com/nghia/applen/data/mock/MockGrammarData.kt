package com.nghia.applen.data.mock

import com.nghia.applen.model.*

object MockGrammarData {
    
    fun getSampleGrammar(): List<Grammar> = listOf(
        Grammar(
            id = "g1",
            title = "Present Simple Tense",
            category = "Tenses",
            level = GrammarLevel.BEGINNER,
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
                
                ## Examples
                - The sun rises in the east. _(general truth)_
                - I wake up at 7 AM every day. _(routine)_
                - The train leaves at 9:30. _(scheduled event)_
                
                ## Common Mistakes
                ❌ He go to school → ✅ He goes to school
                ❌ Do she like pizza? → ✅ Does she like pizza?
            """.trimIndent(),
            estimatedMinutes = 15,
            exercises = listOf(
                Exercise(
                    id = "ex1",
                    question = "She ___ to work by bus every day.",
                    type = ExerciseType.FILL_IN_BLANK,
                    correctAnswer = "goes",
                    options = listOf("go", "goes", "going", "gone"),
                    explanation = "Use 'goes' (base verb + s) for third person singular in present simple."
                ),
                Exercise(
                    id = "ex2",
                    question = "___ you speak French?",
                    type = ExerciseType.FILL_IN_BLANK,
                    correctAnswer = "Do",
                    options = listOf("Do", "Does", "Are", "Is"),
                    explanation = "Use 'Do' for questions with 'you' in present simple."
                )
            ),
            isCompleted = false
        ),
        
        Grammar(
            id = "g2",
            title = "Articles: A, An, The",
            category = "Articles",
            level = GrammarLevel.BEGINNER,
            content = """
                # Articles: A, An, The
                
                ## A vs An (Indefinite Articles)
                Use **a** before consonant sounds:
                - a book, a car, a university (sounds like 'yoo')
                
                Use **an** before vowel sounds:
                - an apple, an hour (silent 'h'), an MBA
                
                ## The (Definite Article)
                Use **the** when:
                - Specific thing known to both speaker and listener
                - Second mention of something
                - Unique things (the sun, the moon)
                - Superlatives (the best, the tallest)
                
                ## No Article (Zero Article)
                Don't use articles with:
                - Plural countable nouns in general
                - Uncountable nouns in general
                - Most proper nouns
                
                ## Examples
                - I saw **a** cat. **The** cat was sleeping.
                - She's **the** tallest girl in class.
                - I like **Ø** music. (general)
                - **The** music at the party was loud. (specific)
            """.trimIndent(),
            estimatedMinutes = 12,
            exercises = listOf(
                Exercise(
                    id = "ex3",
                    question = "She is ___ engineer at Microsoft.",
                    type = ExerciseType.MULTIPLE_CHOICE,
                    correctAnswer = "an",
                    options = listOf("a", "an", "the", "no article"),
                    explanation = "'Engineer' starts with a vowel sound, so we use 'an'."
                ),
                Exercise(
                    id = "ex4",
                    question = "I need to buy ___ new phone. ___ phone I want costs $1000.",
                    type = ExerciseType.FILL_IN_BLANK,
                    correctAnswer = "a, The",
                    options = listOf("a, The", "the, A", "a, A", "the, The"),
                    explanation = "First mention uses 'a'; second mention (specific phone) uses 'the'."
                )
            ),
            isCompleted = true
        ),
        
        Grammar(
            id = "g3",
            title = "Conditional Sentences (Type 1 & 2)",
            category = "Conditionals",
            level = GrammarLevel.INTERMEDIATE,
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
                
                **Use**: Imaginary situations (present/future)
                
                **Examples**:
                - If I had a car, I would drive to work. _(I don't have a car)_
                - If I were you, I would accept the offer. _(I'm not you)_
                
                ## Key Differences
                | Type 1 | Type 2 |
                |--------|--------|
                | Likely to happen | Unlikely/Impossible |
                | If I see him, I'll tell him | If I saw him, I'd tell him |
                | Future possibility | Present/future hypothetical |
                
                ## Note
                - Type 2 uses "were" for all persons (If I were rich...)
                - Can reverse clauses: "I will help you if you ask me."
            """.trimIndent(),
            estimatedMinutes = 20,
            exercises = listOf(
                Exercise(
                    id = "ex5",
                    question = "If I ___ (have) more time, I would learn Spanish.",
                    type = ExerciseType.FILL_IN_BLANK,
                    correctAnswer = "had",
                    options = listOf("have", "had", "will have", "would have"),
                    explanation = "Type 2 conditional uses past simple in the if-clause."
                ),
                Exercise(
                    id = "ex6",
                    question = "If she ___ (call) me, I will answer.",
                    type = ExerciseType.FILL_IN_BLANK,
                    correctAnswer = "calls",
                    options = listOf("call", "calls", "will call", "called"),
                    explanation = "Type 1 conditional uses present simple in the if-clause."
                )
            ),
            isCompleted = false
        ),
        
        Grammar(
            id = "g4",
            title = "Passive Voice",
            category = "Voice",
            level = GrammarLevel.ADVANCED,
            content = """
                # Passive Voice
                
                ## When to Use
                - Focus on the action, not the doer
                - Doer is unknown or unimportant
                - Formal writing
                
                ## Structure
                **Active**: Subject + Verb + Object
                **Passive**: Object + be + past participle + (by + subject)
                
                ## Tense Transformations
                | Tense | Active | Passive |
                |-------|--------|---------|
                | Present Simple | They make cars | Cars are made |
                | Past Simple | He wrote the book | The book was written |
                | Present Perfect | She has finished it | It has been finished |
                | Future | Will build house | House will be built |
                
                ## Examples
                **Active**: Shakespeare wrote Hamlet.
                **Passive**: Hamlet was written by Shakespeare.
                
                **Active**: Someone stole my bike.
                **Passive**: My bike was stolen. _(doer unknown)_
                
                ## Notes
                - Use passive when doer is obvious or unimportant
                - Common in scientific/academic writing
                - Can omit "by + agent" if not important
            """.trimIndent(),
            estimatedMinutes = 25,
            exercises = listOf(
                Exercise(
                    id = "ex7",
                    question = "The Mona Lisa ___ (paint) by Leonardo da Vinci.",
                    type = ExerciseType.FILL_IN_BLANK,
                    correctAnswer = "was painted",
                    options = listOf("painted", "was painted", "is painted", "has painted"),
                    explanation = "Past simple passive: was/were + past participle."
                ),
                Exercise(
                    id = "ex8",
                    question = "English ___ (speak) in many countries.",
                    type = ExerciseType.FILL_IN_BLANK,
                    correctAnswer = "is spoken",
                    options = listOf("speaks", "is spoken", "was spoken", "has spoken"),
                    explanation = "Present simple passive: am/is/are + past participle."
                )
            ),
            isCompleted = false
        ),
        
        Grammar(
            id = "g5",
            title = "Modal Verbs: Can, Could, May, Might",
            category = "Modals",
            level = GrammarLevel.INTERMEDIATE,
            content = """
                # Modal Verbs
                
                ## Can
                **Ability**: I can swim.
                **Permission** (informal): Can I use your phone?
                **Possibility**: It can get very cold here.
                
                ## Could
                **Past ability**: I could run fast when I was young.
                **Polite request**: Could you help me, please?
                **Possibility**: It could rain tomorrow.
                
                ## May
                **Permission** (formal): May I come in?
                **Possibility**: She may be late.
                
                ## Might
                **Lower possibility**: I might go to the party (maybe not).
                **Polite suggestion**: You might want to check that.
                
                ## Comparison
                | Modal | Certainty Level |
                |-------|-----------------|
                | will | 95% certain |
                | can/could/may | 50-80% |
                | might | 30-50% |
                
                ## Rules
                - Modals + base verb (no 'to')
                - No -s in third person
                - ✅ She can swim
                - ❌ She cans swim
                - ❌ She can to swim
            """.trimIndent(),
            estimatedMinutes = 18,
            exercises = listOf(
                Exercise(
                    id = "ex9",
                    question = "___ you speak Japanese?",
                    type = ExerciseType.MULTIPLE_CHOICE,
                    correctAnswer = "Can",
                    options = listOf("Can", "Could", "May", "Might"),
                    explanation = "Use 'Can' to ask about present ability."
                ),
                Exercise(
                    id = "ex10",
                    question = "It ___ snow tomorrow, but I'm not sure.",
                    type = ExerciseType.MULTIPLE_CHOICE,
                    correctAnswer = "might",
                    options = listOf("can", "could", "may", "might"),
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
