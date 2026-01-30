package com.nghia.applen.android.ui.screens.social

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.nghia.applen.android.ui.viewmodel.SocialViewModel
import com.nghia.applen.model.*
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardScreen(
    navController: NavController,
    viewModel: SocialViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Leaderboards") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Period selector
            PeriodSelector(
                selected = uiState.selectedPeriod,
                onPeriodSelected = { viewModel.changePeriod(it) }
            )
            
            // Type tabs
            LeaderboardTypeTabs(
                selected = uiState.selectedType,
                onTypeSelected = { viewModel.changeType(it) }
            )
            
            // Leaderboard content
            val currentLeaderboard = uiState.leaderboards[uiState.selectedType]
            
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (currentLeaderboard != null) {
                LeaderboardList(
                    leaderboard = currentLeaderboard,
                    modifier = Modifier.fillMaxSize()
                )
            } else {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No data available")
                }
            }
        }
    }
}

@Composable
private fun PeriodSelector(
    selected: LeaderboardPeriod,
    onPeriodSelected: (LeaderboardPeriod) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = LeaderboardPeriod.entries.indexOf(selected),
        modifier = Modifier.fillMaxWidth()
    ) {
        LeaderboardPeriod.entries.forEach { period ->
            Tab(
                selected = selected == period,
                onClick = { onPeriodSelected(period) },
                text = { Text(period.name.lowercase().capitalize()) }
            )
        }
    }
}

@Composable
private fun LeaderboardTypeTabs(
    selected: LeaderboardType,
    onTypeSelected: (LeaderboardType) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = LeaderboardType.entries.indexOf(selected),
        modifier = Modifier.fillMaxWidth()
    ) {
        LeaderboardType.entries.forEach { type ->
            Tab(
                selected = selected == type,
                onClick = { onTypeSelected(type) },
                text = { 
                    Text(
                        when (type) {
                            LeaderboardType.TOTAL_SCORE -> "Score"
                            LeaderboardType.STREAK -> "Streak"
                            LeaderboardType.WORDS_LEARNED -> "Words"
                            LeaderboardType.TESTS_COMPLETED -> "Tests"
                            LeaderboardType.STUDY_TIME -> "Time"
                        }
                    )
                }
            )
        }
    }
}

@Composable
private fun LeaderboardList(
    leaderboard: Leaderboard,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(leaderboard.entries) { entry ->
            LeaderboardEntryCard(entry)
        }
    }
}

@Composable
private fun LeaderboardEntryCard(entry: LeaderboardEntry) {
    val isCurrentUser = entry.userId == "current"
    
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = if (isCurrentUser) CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ) else CardDefaults.cardColors()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Rank with badge
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.width(60.dp)
            ) {
                if (entry.badge != null) {
                    val badgeText = entry.badge  // Local variable for smart cast
                    Text(
                        text = badgeText,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                Text(
                    text = "#${entry.rank}",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                
                // Rank change indicator
                entry.change?.let { change ->
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            if (change > 0) Icons.Default.TrendingUp
                            else if (change < 0) Icons.Default.TrendingDown
                            else Icons.Default.Remove,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = when {
                                change > 0 -> MaterialTheme.colorScheme.primary
                                change < 0 -> MaterialTheme.colorScheme.error
                                else -> MaterialTheme.colorScheme.onSurfaceVariant
                            }
                        )
                        Text(
                            text = if (change > 0) "+$change" else "$change",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Avatar
            Surface(
                modifier = Modifier.size(48.dp),
                shape = MaterialTheme.shapes.medium,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = entry.userName.first().toString(),
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
            
            Spacer(modifier = Modifier.width(16.dp))
            
            // Name and score
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = entry.userName,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${entry.score} points",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}
