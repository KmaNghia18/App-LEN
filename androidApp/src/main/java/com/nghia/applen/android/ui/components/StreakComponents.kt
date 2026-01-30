package com.nghia.applen.android.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StreakCounter(
    streak: Int,
    isStudiedToday: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "streak_flame")
    val rotation by infiniteTransition.animateFloat(
        initialValue = -10f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "flame_rotation"
    )
    
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (isStudiedToday) 
                MaterialTheme.colorScheme.primaryContainer 
            else 
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Animated flame emoji
            if (streak > 0) {
                Text(
                    text = "🔥",
                    fontSize = 32.sp,
                    modifier = Modifier.rotate(rotation)
                )
            } else {
                Text(
                    text = "💤",
                    fontSize = 32.sp
                )
            }
            
            Column {
                Text(
                    text = if (streak > 0) "$streak Day Streak!" else "Start Your Streak",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = if (isStudiedToday) 
                        "Great job today! ✓" 
                    else if (streak > 0)
                        "Study today to continue"
                    else
                        "Study today to start",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
fun AchievementBadge(
    title: String,
    icon: String,
    isUnlocked: Boolean,
    progress: Float = 0f,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = if (isUnlocked) 
                MaterialTheme.colorScheme.tertiaryContainer 
            else 
                MaterialTheme.colorScheme.surfaceVariant
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = icon,
                fontSize = 40.sp,
                modifier = Modifier.alpha(if (isUnlocked) 1f else 0.3f)
            )
            
            Text(
                text = title,
                style = MaterialTheme.typography.labelMedium,
                fontWeight = if (isUnlocked) FontWeight.Bold else FontWeight.Normal
            )
            
            if (!isUnlocked && progress > 0) {
                LinearProgressIndicator(
                    progress = progress,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}
