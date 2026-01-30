package com.nghia.applen.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MasteryIndicator(
    masteryLevel: Int,
    maxLevel: Int = 5,
    modifier: Modifier = Modifier
) {
    val progress = masteryLevel.toFloat() / maxLevel.toFloat()
    val color = when (masteryLevel) {
        0 -> Color(0xFFCCCCCC) // Gray
        1 -> Color(0xFFFF5252) // Red
        2 -> Color(0xFFFF9800) // Orange
        3 -> Color(0xFFFFC107) // Amber
        4 -> Color(0xFF4CAF50) // Green
        5 -> Color(0xFF2196F3) // Blue
        else -> Color(0xFFCCCCCC)
    }
    
    Surface(
        modifier = modifier.size(48.dp),
        shape = CircleShape,
        color = color.copy(alpha = 0.2f),
        contentColor = color
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            CircularProgressIndicator(
                progress = progress,
                modifier = Modifier.fillMaxSize().padding(4.dp),
                color = color,
                strokeWidth = 4.dp
            )
            Text(
                text = "$masteryLevel",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = color
            )
        }
    }
}

@Composable
fun LevelBadge(
    level: String,
    modifier: Modifier = Modifier
) {
    val (color, textColor) = when (level.uppercase()) {
        "BEGINNER" -> Pair(Color(0xFF4CAF50), Color.White)
        "INTERMEDIATE" -> Pair(Color(0xFFFFC107), Color.Black)
        "ADVANCED" -> Pair(Color(0xFFFF9800), Color.White)
        "EXPERT" -> Pair(Color(0xFFFF5252), Color.White)
        else -> Pair(Color.Gray, Color.White)
    }
    
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = color
    ) {
        Text(
            text = level.uppercase(),
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            color = textColor,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
