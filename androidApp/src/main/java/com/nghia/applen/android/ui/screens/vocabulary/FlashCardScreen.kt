package com.nghia.applen.android.ui.screens.vocabulary

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nghia.applen.android.ui.components.FlipCard
import com.nghia.applen.android.ui.components.SwipeableCard
import com.nghia.applen.android.ui.viewmodel.VocabularyViewModel
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCardScreen(
    navController: NavController,
    viewModel: VocabularyViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val currentCard = uiState.currentCard
    val progress = uiState.reviewProgress
    
    var isFlipped by remember { mutableStateOf(false) }
    var showCongrats by remember { mutableStateOf(false) }
    
    LaunchedEffect(currentCard) {
        isFlipped = false
        if (currentCard == null && progress.completedCards > 0) {
            showCongrats = true
        }
    }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Review Session") },
                navigationIcon = {
                    IconButton(onClick = { 
                        viewModel.endReviewSession()
                        navController.popBackStack()
                    }) {
                        Icon(Icons.Default.Close, "Close")
                    }
                }
            )
        }
    ) { paddingValues ->
        if (showCongrats) {
            // Congratulations screen
            CongratsScreen(
                progress = progress,
                onFinish = {
                    viewModel.endReviewSession()
                    navController.popBackStack()
                }
            )
        } else if (currentCard != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Progress indicator
                ProgressBar(
                    completed = progress.completedCards,
                    total = progress.totalCards,
                    correct = progress.correctCount,
                    incorrect = progress.incorrectCount
                )
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Flashcard
                SwipeableCard(
                    onSwipeLeft = {
                        viewModel.reviewCard(knows = false)
                        isFlipped = false
                    },
                    onSwipeRight = {
                        viewModel.reviewCard(knows = true)
                        isFlipped = false
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    FlipCard(
                        front = { CardFront(vocabulary = currentCard) },
                        back = { CardBack(vocabulary = currentCard) },
                        isFlipped = isFlipped,
                        onFlip = { isFlipped = !isFlipped }
                    )
                }
                
                Spacer(modifier = Modifier.height(24.dp))
                
                // Controls
                if (!isFlipped) {
                    Button(
                        onClick = { isFlipped = true },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Show Answer")
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Button(
                            onClick = { 
                                viewModel.reviewCard(knows = false)
                                isFlipped = false
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error
                            )
                        ) {
                            Icon(Icons.Default.Close, null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Don't Know")
                        }
                        
                        Button(
                            onClick = { 
                                viewModel.reviewCard(knows = true)
                                isFlipped = false
                            },
                            modifier = Modifier.weight(1f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Icon(Icons.Default.Check, null)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text("Know")
                        }
                    }
                }
                
                // Swipe hint
                if (!isFlipped) {
                    Text(
                        text = "Tap to flip • Swipe left (don't know) or right (know)",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun ProgressBar(
    completed: Int,
    total: Int,
    correct: Int,
    incorrect: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${completed}/$total",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Check,
                        null,
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("$correct")
                }
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Close,
                        null,
                        tint = MaterialTheme.colorScheme.error,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("$incorrect")
                }
            }
        }
        
        Spacer(modifier = Modifier.height(8.dp))
        
        LinearProgressIndicator(
            progress = completed.toFloat() / total.toFloat(),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun CardFront(vocabulary: com.nghia.applen.model.Vocabulary) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = vocabulary.word,
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = vocabulary.phonetic,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(8.dp))
        
        Surface(
            shape = MaterialTheme.shapes.small,
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Text(
                text = vocabulary.partOfSpeech,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
        
        if (vocabulary.audioUrl != null) {
            Spacer(modifier = Modifier.height(24.dp))
            
            IconButton(
                onClick = { /* Play audio */ },
                modifier = Modifier.size(64.dp)
            ) {
                Icon(
                    Icons.Default.VolumeUp,
                    "Play pronunciation",
                    modifier = Modifier.size(40.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun CardBack(vocabulary: com.nghia.applen.model.Vocabulary) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = vocabulary.word,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        
        Divider()
        
        // Definitions
        vocabulary.definitions.forEach { definition ->
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = definition.meaning,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                
                if (definition.example != null) {
                    Text(
                        text = "\"${definition.example}\"",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontStyle = androidx.compose.ui.text.font.FontStyle.Italic
                    )
                }
            }
        }
        
        // Examples
        if (vocabulary.examples.isNotEmpty()) {
            Divider()
            Text(
                text = "Examples:",
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold
            )
            vocabulary.examples.take(2).forEach { example ->
                Text(
                    text = "• $example",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        
        // Synonyms/Antonyms
        if (vocabulary.synonyms.isNotEmpty() || vocabulary.antonyms.isNotEmpty()) {
            Divider()
            
            if (vocabulary.synonyms.isNotEmpty()) {
                Text(
                    text = "Synonyms: ${vocabulary.synonyms.take(3).joinToString(", ")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            
            if (vocabulary.antonyms.isNotEmpty()) {
                Text(
                    text = "Antonyms: ${vocabulary.antonyms.take(3).joinToString(", ")}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
private fun CongratsScreen(
    progress: com.nghia.applen.android.ui.viewmodel.ReviewProgress,
    onFinish: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "🎉",
            style = MaterialTheme.typography.displayLarge,
            fontSize = MaterialTheme.typography.displayLarge.fontSize * 2
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Text(
            text = "Review Complete!",
            style = MaterialTheme.typography.displaySmall,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = "You reviewed ${progress.totalCards} cards",
            style = MaterialTheme.typography.titleMedium,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    StatCard(
                        label = "Correct",
                        value = progress.correctCount.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )
                    StatCard(
                        label = "Incorrect",
                        value = progress.incorrectCount.toString(),
                        color = MaterialTheme.colorScheme.error
                    )
                    StatCard(
                        label = "Accuracy",
                        value = "${(progress.correctCount * 100 / progress.totalCards)}%",
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Button(
            onClick = onFinish,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Finish")
        }
    }
}

@Composable
private fun StatCard(
    label: String,
    value: String,
    color: androidx.compose.ui.graphics.Color
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = value,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = color
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall
        )
    }
}
