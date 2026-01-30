@file:OptIn(ExperimentalMaterial3Api::class)
package com.nghia.applen.android.ui.screens.quiz

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
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
import com.nghia.applen.android.ui.Screen
import com.nghia.applen.android.ui.viewmodel.QuizViewModel
import com.nghia.applen.model.Question
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizPlayerScreen(
    navController: NavController,
    viewModel: QuizViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // Show results if quiz completed
    if (uiState.showResults) {
        QuizResultsScreen(
            navController = navController,
            viewModel = viewModel
        )
        return
    }
    
    // Exit confirmation dialog
    var showExitDialog by remember { mutableStateOf(false) }
    
    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = { Text("Exit Quiz?") },
            text = { Text("Your progress will be lost. Are you sure?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.exitQuiz()
                        navController.popBackStack()
                    }
                ) {
                    Text("Exit")
                }
            },
            dismissButton = {
                TextButton(onClick = { showExitDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
    
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(uiState.currentQuiz?.title ?: "Quiz") },
                navigationIcon = {
                    IconButton(onClick = { showExitDialog = true }) {
                        Icon(Icons.Default.Close, "Exit")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            )
        },
        bottomBar = {
            QuizNavigationBar(
                currentIndex = uiState.currentQuestionIndex,
                totalQuestions = uiState.currentQuiz?.questions?.size ?: 0,
                onPrevious = { viewModel.previousQuestion() },
                onNext = { viewModel.nextQuestion() },
                onSubmit = { viewModel.submitQuiz() }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Timer and progress
            TimerAndProgress(
                timeRemainingSeconds = uiState.timeRemainingSeconds,
                currentQuestion = uiState.currentQuestionIndex + 1,
                totalQuestions = uiState.currentQuiz?.questions?.size ?: 0
            )
            
            Divider()
            
            // Question
            uiState.currentQuestion?.let { question ->
                QuestionContent(
                    question = question,
                    selectedAnswer = viewModel.getUserAnswer(question.id),
                    onAnswerSelected = { answer ->
                        viewModel.submitAnswer(answer)
                    }
                )
            }
        }
    }
}

@Composable
private fun TimerAndProgress(
    timeRemainingSeconds: Int,
    currentQuestion: Int,
    totalQuestions: Int
) {
    val minutes = timeRemainingSeconds / 60
    val seconds = timeRemainingSeconds % 60
    val isLowTime = timeRemainingSeconds < 60
    
    // Pulsing animation for low time
    val alpha by animateFloatAsState(
        targetValue = if (isLowTime && timeRemainingSeconds % 2 == 0) 1f else 0.5f,
        animationSpec = tween(500),
        label = "timer_pulse"
    )
    
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isLowTime)
                MaterialTheme.colorScheme.errorContainer
            else
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Timer
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        Icons.Default.Timer,
                        contentDescription = "Time",
                        tint = if (isLowTime)
                            MaterialTheme.colorScheme.error.copy(alpha = alpha)
                        else
                            MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = String.format("%02d:%02d", minutes, seconds),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = if (isLowTime)
                            MaterialTheme.colorScheme.error.copy(alpha = alpha)
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                }
                
                // Progress
                Text(
                    text = "Question $currentQuestion / $totalQuestions",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            LinearProgressIndicator(
                progress = currentQuestion.toFloat() / totalQuestions.toFloat(),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun QuestionContent(
    question: Question,
    selectedAnswer: String?,
    onAnswerSelected: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Question number
        Text(
            text = "Question ${question.questionNumber}",
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.primary
        )
        
        // Question text
        Text(
            text = question.questionText,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        // Answer options
        question.options.forEach { option ->
            val isSelected = selectedAnswer == option
            
            OutlinedCard(
                onClick = { onAnswerSelected(option) },
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.outlinedCardColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.surface
                ),
                border = if (isSelected)
                    CardDefaults.outlinedCardBorder().copy(
                        width = 2.dp,
                        brush = androidx.compose.ui.graphics.SolidColor(
                            MaterialTheme.colorScheme.primary
                        )
                    )
                else
                    CardDefaults.outlinedCardBorder()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = option,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.weight(1f)
                    )
                    
                    if (isSelected) {
                        Icon(
                            Icons.Default.CheckCircle,
                            contentDescription = "Selected",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuizNavigationBar(
    currentIndex: Int,
    totalQuestions: Int,
    onPrevious: () -> Unit,
    onNext: () -> Unit,
    onSubmit: () -> Unit
) {
    BottomAppBar {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Previous button
            OutlinedButton(
                onClick = onPrevious,
                enabled = currentIndex > 0
            ) {
                Icon(Icons.Default.ArrowBack, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Previous")
            }
            
            // Next or Submit button
            if (currentIndex < totalQuestions - 1) {
                Button(onClick = onNext) {
                    Text("Next")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.ArrowForward, null)
                }
            } else {
                Button(
                    onClick = onSubmit,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.tertiary
                    )
                ) {
                    Icon(Icons.Default.Done, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Submit Quiz")
                }
            }
        }
    }
}

@Composable
private fun QuizResultsScreen(
    navController: NavController,
    viewModel: QuizViewModel
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val quiz = uiState.currentQuiz ?: return
    val isPassed = uiState.score >= quiz.passingScore
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Quiz Results") },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.exitQuiz()
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Home.route) { inclusive = false }
                            }
                        }
                    ) {
                        Icon(Icons.Default.Close, "Close")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            
            // Result icon
            Text(
                text = if (isPassed) "🎉" else "📚",
                style = MaterialTheme.typography.displayLarge
            )
            
            // Pass/Fail message
            Text(
                text = if (isPassed) "Congratulations!" else "Keep Practicing!",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )
            
            // Score card
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = if (isPassed)
                        MaterialTheme.colorScheme.primaryContainer
                    else
                        MaterialTheme.colorScheme.errorContainer
                )
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = "Your Score",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Text(
                        text = "${uiState.score}%",
                        style = MaterialTheme.typography.displayLarge,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Text(
                        text = "${uiState.correctCount} / ${quiz.questions.size} correct",
                        style = MaterialTheme.typography.titleMedium
                    )
                    
                    Divider()
                    
                    Text(
                        text = if (isPassed)
                            "You passed! Passing score: ${quiz.passingScore}%"
                        else
                            "You need ${quiz.passingScore}% to pass. Try again!",
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Spacer(modifier = Modifier.weight(1f))
            
            // Action buttons
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.startQuiz(quiz.id)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Default.Refresh, null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Try Again")
                }
                
                OutlinedButton(
                    onClick = {
                        viewModel.exitQuiz()
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Back to Quizzes")
                }
            }
        }
    }
}
