package com.nghia.applen.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AudioPlayer(
    audioUrl: String?,
    modifier: Modifier = Modifier,
    autoPlay: Boolean = false,
    onPlaybackStateChanged: (Boolean) -> Unit = {}
) {
    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableStateOf(0f) }
    var duration by remember { mutableStateOf(0f) }
    
    // Auto-play effect
    LaunchedEffect(audioUrl, autoPlay) {
        if (autoPlay && audioUrl != null) {
            isPlaying = true
            onPlaybackStateChanged(true)
            // TODO: Implement actual audio playback with MediaPlayer
            // For now, simulate playback
            kotlinx.coroutines.delay(2000)
            isPlaying = false
            onPlaybackStateChanged(false)
        }
    }
    
    if (audioUrl != null) {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    // Play/Pause button
                    IconButton(
                        onClick = {
                            isPlaying = !isPlaying
                            onPlaybackStateChanged(isPlaying)
                            // TODO: Implement play/pause logic
                        }
                    ) {
                        Icon(
                            imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.size(32.dp)
                        )
                    }
                    
                    // Progress slider
                    Slider(
                        value = currentPosition,
                        onValueChange = { currentPosition = it },
                        valueRange = 0f..duration,
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Volume icon
                    Icon(
                        Icons.Default.VolumeUp,
                        contentDescription = "Volume",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                
                // Time display
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatTime(currentPosition),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Text(
                        text = formatTime(duration),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@Composable
fun SimpleAudioButton(
    audioUrl: String?,
    modifier: Modifier = Modifier,
    autoPlay: Boolean = false
) {
    var isPlaying by remember { mutableStateOf(false) }
    
    LaunchedEffect(audioUrl, autoPlay) {
        if (autoPlay && audioUrl != null) {
            isPlaying = true
            // Simulate playback
            kotlinx.coroutines.delay(2000)
            isPlaying = false
        }
    }
    
    if (audioUrl != null) {
        IconButton(
            onClick = {
                isPlaying = !isPlaying
                // TODO: Implement actual playback
            },
            modifier = modifier
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.VolumeUp else Icons.Default.VolumeOff,
                contentDescription = if (isPlaying) "Playing" else "Play pronunciation",
                tint = if (isPlaying) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

private fun formatTime(seconds: Float): String {
    val minutes = (seconds / 60).toInt()
    val secs = (seconds % 60).toInt()
    return String.format("%02d:%02d", minutes, secs)
}
