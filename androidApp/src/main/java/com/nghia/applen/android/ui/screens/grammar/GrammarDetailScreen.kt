package com.nghia.applen.android.ui.screens.grammar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nghia.applen.android.ui.components.MarkdownText
import com.nghia.applen.android.ui.viewmodel.GrammarViewModel
import com.nghia.applen.model.Exercise
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GrammarDetailScreen(
    grammarId: String,
    navController: NavController,
    viewModel: GrammarViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val grammar = uiState.currentLesson
    
    var showExercises by remember { mutableStateOf(false) }
    
    LaunchedEffect(grammarId) {
        viewModel.selectLesson(grammarId)
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(grammar?.title ?: "Grammar Lesson") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        },
        bottomBar = {
            if (grammar != null && grammar.exercises.isNotEmpty()) {
                BottomAppBar {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (!showExercises) {
                            Button(
                                onClick = { showExercises = true },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.EditNote, null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Practice Exercises (${grammar.exercises.size})")
                            }
                        } else {
                            Button(
                                onClick = { showExercises = false },
                                modifier = Modifier.weight(1f)
                            ) {
                                Icon(Icons.Default.Book, null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Back to Lesson")
                            }
                        }
                        
                        if (!grammar.isCompleted) {
                            Spacer(modifier = Modifier.width(8.dp))
                            IconButton(
                                onClick = {
                                    viewModel.completeLesson(grammar.id)
                                }
                            ) {
                                Icon(Icons.Default.CheckCircle, "Mark as Complete")
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        if (grammar == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                if (!showExercises) {
                    // Lesson content
                    MarkdownText(
                        markdown = grammar.content,
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    // Exercises
                    ExercisesSection(
                        exercises = grammar.exercises,
                        exerciseResults = uiState.exerciseResults,
                        onSubmitAnswer = { exerciseId, answer ->
                            viewModel.submitAnswer(exerciseId, answer)
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun ExercisesSection(
    exercises: List<Exercise>,
    exerciseResults: Map<String, Boolean>,
    onSubmitAnswer: (String, String) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Practice Exercises",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        exercises.forEachIndexed { index, exercise ->
            ExerciseCard(
                index = index + 1,
                exercise = exercise,
                isCorrect = exerciseResults[exercise.id],
                onSubmitAnswer = { answer ->
                    onSubmitAnswer(exercise.id, answer)
                }
            )
        }
        
        // Summary
        if (exerciseResults.size == exercises.size) {
            val correctCount = exerciseResults.values.count { it }
            val score = (correctCount * 100) / exercises.size
            
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (score >= 70) 
                        MaterialTheme.colorScheme.primaryContainer 
                    else 
                        MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (score >= 70) "Great Job! 🎉" else "Keep Practicing! 💪",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(8.dp))
                    
                    Text(
                        text = "Score: $correctCount / ${exercises.size} ($score%)",
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}

@Composable
private fun ExerciseCard(
    index: Int,
    exercise: Exercise,
    isCorrect: Boolean?,
    onSubmitAnswer: (String) -> Unit
) {
    var selectedAnswer by remember { mutableStateOf("") }
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = when (isCorrect) {
                true -> MaterialTheme.colorScheme.primaryContainer
                false -> MaterialTheme.colorScheme.errorContainer
                null -> MaterialTheme.colorScheme.surface
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Question $index",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            
            Text(
                text = exercise.question,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            // Answer options
            exercise.options.forEach { option ->
                ElevatedButton(
                    onClick = {
                        if (isCorrect == null) {
                            selectedAnswer = option
                            onSubmitAnswer(option)
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = isCorrect == null,
                    colors = ButtonDefaults.elevatedButtonColors(
                        containerColor = when {
                            isCorrect == true && option == exercise.options[exercise.correctAnswer] -> 
                                MaterialTheme.colorScheme.primary
                            isCorrect == false && option == selectedAnswer -> 
                                MaterialTheme.colorScheme.error
                            else -> MaterialTheme.colorScheme.surface
                        }
                    )
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(option)
                        
                        if (isCorrect != null) {
                            when {
                                option == exercise.options[exercise.correctAnswer] -> {
                                    Icon(Icons.Default.CheckCircle, "Correct", 
                                        tint = MaterialTheme.colorScheme.primary)
                                }
                                option == selectedAnswer && option != exercise.options[exercise.correctAnswer] -> {
                                    Icon(Icons.Default.Cancel, "Wrong", 
                                        tint = MaterialTheme.colorScheme.error)
                                }
                            }
                        }
                    }
                }
            }
            
            // Explanation (shown after answering)
            if (isCorrect != null && exercise.explanation != null) {
                Divider()
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Default.Lightbulb,
                        null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = exercise.explanation,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}
