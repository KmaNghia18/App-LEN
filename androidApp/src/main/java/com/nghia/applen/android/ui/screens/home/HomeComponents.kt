package com.nghia.applen.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.nghia.applen.android.ui.components.AchievementBadge
import com.nghia.applen.android.ui.components.StreakCounter
import com.nghia.applen.model.Vocabulary

@Composable
fun DailyWordCard(
    word: Vocabulary,
    onLearnMore: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "📖 Word of the Day",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSecondaryContainer
                )
                
                Surface(
                    shape = MaterialTheme.shapes.small,
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    Text(
                        text = word.level,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }
            }
            
            Text(
                text = word.word,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            
            Text(
                text = word.pronunciation,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.7f)
            )
            
            Text(
                text = word.definition,
                style = MaterialTheme.typography.bodyMedium
            )
            
            Button(
                onClick = onLearnMore,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Learn More")
            }
        }
    }
}

@Composable
fun StreakSection(
    currentStreak: Int,
    longestStreak: Int,
    isStudiedToday: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Your Progress",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        StreakCounter(
            streak = currentStreak,
            isStudiedToday = isStudiedToday
        )
        
        if (longestStreak > currentStreak) {
            Text(
                text = "🏆 Longest streak: $longestStreak days",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
fun AchievementsSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Achievements",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(horizontal = 4.dp)
        ) {
            items(getSampleAchievements()) { achievement ->
                AchievementBadge(
                    title = achievement.title,
                    icon = achievement.icon,
                    isUnlocked = achievement.isUnlocked,
                    progress = achievement.progress,
                    modifier = Modifier.width(120.dp)
                )
            }
        }
    }
}

private data class Achievement(
    val title: String,
    val icon: String,
    val isUnlocked: Boolean,
    val progress: Float = 0f
)

private fun getSampleAchievements() = listOf(
    Achievement("First Steps", "👶", true),
    Achievement("Week Warrior", "💪", true),
    Achievement("Century Club", "💯", false, 0.67f),
    Achievement("Streak Master", "🔥", false, 0.3f),
    Achievement("Polyglot", "🌍", false, 0.1f)
)
