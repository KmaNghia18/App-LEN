package com.nghia.applen.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nghia.applen.android.ui.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("App-LEN") },
                actions = {
                    IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                        Icon(Icons.Default.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { navController.navigate(Screen.Profile.route) }) {
                        Icon(Icons.Default.Person, contentDescription = "Profile")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Home, contentDescription = null) },
                    label = { Text("Home") },
                    selected = true,
                    onClick = { }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Book, contentDescription = null) },
                    label = { Text("Learn") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Vocabulary.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.Quiz, contentDescription = null) },
                    label = { Text("Practice") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Quiz.route) }
                )
                NavigationBarItem(
                    icon = { Icon(Icons.Default.TrendingUp, contentDescription = null) },
                    label = { Text("Progress") },
                    selected = false,
                    onClick = { navController.navigate(Screen.Progress.route) }
                )
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp), // Changed to horizontal padding only
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Add top padding
                GreetingSection()
            }
            
            item {
                DailyGoalCard()
            }
            
            // Quick Access Cards
            item {
                Text(
                    text = "Quick Access",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                )
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickAccessCard(
                        title = "Vocabulary",
                        icon = Icons.Default.Book,
                        description = "10 words",
                        onClick = { navController.navigate(Screen.Vocabulary.route) }
                    )
                    
                    QuickAccessCard(
                        title = "Grammar",
                        icon = Icons.Default.School,
                        description = "5 lessons",
                        onClick = { navController.navigate(Screen.Grammar.route) }
                    )
                }
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickAccessCard(
                        title = "Practice",
                        icon = Icons.Default.Quiz,
                        description = "6 tests",
                        onClick = { navController.navigate(Screen.Quiz.route) }
                    )
                    
                    QuickAccessCard(
                        title = "Progress",
                        icon = Icons.Default.TrendingUp,
                        description = "Track stats",
                        onClick = { navController.navigate(Screen.Progress.route) }
                    )
                }
            }
            
            // Social Section
            item {
                Text(
                    text = "Social",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 24.dp, bottom = 8.dp)
                )
            }
            
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    QuickAccessCard(
                        title = "Leaderboard",
                        icon = Icons.Default.Leaderboard,
                        description = "Top players",
                        onClick = { navController.navigate(Screen.Social.route) }
                    )
                    
                    QuickAccessCard(
                        title = "Friends",
                        icon = Icons.Default.People,
                        description = "3 friends",
                        onClick = { navController.navigate(Screen.Social.route) }
                    )
                }
            }
            
            item {
                QuickAccessCard(
                    title = "Challenges",
                    icon = Icons.Default.Star,
                    description = "2 active challenges",
                    onClick = { navController.navigate(Screen.Quiz.route) }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp)) // Add bottom padding
            }
        }
    }
}

@Composable
private fun GreetingSection() {
    Column {
        Text(
            text = "Hello, Learner! 👋",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Ready to improve your English today?",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun DailyGoalCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Daily Goal",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "0/20 words",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            
            LinearProgressIndicator(
                progress = 0f,
                modifier = Modifier.fillMaxWidth()
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "🔥 0",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Day streak",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "⏱️ 0 min",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Study time",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuickAccessCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(40.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

private data class QuickAccessItem(
    val title: String,
    val description: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
)

private fun getQuickAccessItems() = listOf(
    QuickAccessItem(
        title = "Vocabulary",
        description = "Learn new words with flashcards",
        icon = Icons.Default.Book,
        route = Screen.Vocabulary.route
    ),
    QuickAccessItem(
        title = "Grammar",
        description = "Master English grammar rules",
        icon = Icons.Default.MenuBook,
        route = Screen.Grammar.route
    ),
    QuickAccessItem(
        title = "Practice Tests",
        description = "TOEIC & IELTS practice questions",
        icon = Icons.Default.Quiz,
        route = Screen.Quiz.route
    )
)
