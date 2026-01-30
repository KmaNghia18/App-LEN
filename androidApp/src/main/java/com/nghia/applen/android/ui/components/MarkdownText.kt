package com.nghia.applen.android.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Simple Markdown viewer component
 * Supports: Headers, Bold, Italic, Code, Lists, Tables
 */
@Composable
fun MarkdownText(
    markdown: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val lines = markdown.lines()
        var i = 0
        
        while (i < lines.size) {
            val line = lines[i]
            
            when {
                // Headers
                line.startsWith("# ") -> {
                    Text(
                        text = line.removePrefix("# "),
                        style = MaterialTheme.typography.headlineLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
                line.startsWith("## ") -> {
                    Text(
                        text = line.removePrefix("## "),
                        style = MaterialTheme.typography.headlineMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                }
                line.startsWith("### ") -> {
                    Text(
                        text = line.removePrefix("### "),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
                
                // Bullet lists
                line.trim().startsWith("- ") -> {
                    Row(modifier = Modifier.padding(start = 16.dp)) {
                        Text("• ", style = MaterialTheme.typography.bodyLarge)
                        Text(
                            text = line.trim().removePrefix("- "),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
                
                // Tables (simplified)
                line.startsWith("|") && line.endsWith("|") -> {
                    // Skip table header separator
                    if (line.contains("---")) {
                        i++
                        continue
                    }
                    
                    val cells = line.split("|").filter { it.isNotBlank() }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        cells.forEach { cell ->
                            Text(
                                text = cell.trim(),
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.weight(1f).padding(4.dp)
                            )
                        }
                    }
                }
                
                // Bold text **text**
                line.contains("**") -> {
                    val parts = line.split("**")
                    Row {
                        parts.forEachIndexed { index, part ->
                            Text(
                                text = part,
                                fontWeight = if (index % 2 == 1) FontWeight.Bold else FontWeight.Normal,
                                style = MaterialTheme.typography.bodyLarge
                            )
                        }
                    }
                }
                
                // Italic _text_
                line.contains("_(") && line.contains(")_") -> {
                    val regex = "_\\(([^)]+)\\)_".toRegex()
                    val match = regex.find(line)
                    if (match != null) {
                        Text(
                            text = line.replace(regex, match.groupValues[1]),
                            fontStyle = FontStyle.Italic,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    } else {
                        Text(text = line, style = MaterialTheme.typography.bodyLarge)
                    }
                }
                
                // Code blocks (inline)
                line.trim().startsWith("```") -> {
                    // Skip code fence markers
                    i++
                    val codeLines = mutableListOf<String>()
                    while (i < lines.size && !lines[i].trim().startsWith("```")) {
                        codeLines.add(lines[i])
                        i++
                    }
                    
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.small,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = codeLines.joinToString("\n"),
                            fontFamily = FontFamily.Monospace,
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(12.dp)
                        )
                    }
                }
                
                // Checkmarks and crosses
                line.contains("✅") || line.contains("❌") -> {
                    Text(
                        text = line,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 16.sp
                    )
                }
                
                // Empty line
                line.isBlank() -> {
                    Spacer(modifier = Modifier.height(4.dp))
                }
                
                // Regular text
                else -> {
                    Text(
                        text = line,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
            
            i++
        }
    }
}
