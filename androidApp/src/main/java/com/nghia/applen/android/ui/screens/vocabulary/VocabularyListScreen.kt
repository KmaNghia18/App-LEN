package com.nghia.applen.android.ui.screens.vocabulary

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
import com.nghia.applen.android.ui.Screen
import com.nghia.applen.android.ui.components.LevelBadge
import com.nghia.applen.android.ui.components.MasteryIndicator
import com.nghia.applen.android.ui.viewmodel.VocabularyViewModel
import com.nghia.applen.model.Vocabulary
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VocabularyListScreen(
    navController: NavController,
    viewModel: VocabularyViewModel = koinViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var searchQuery by remember { mutableStateOf("") }
    var showFilterSheet by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Vocabulary") },
                actions = {
                    IconButton(onClick = { showFilterSheet = true }) {
                        Icon(Icons.Default.FilterList, "Filter")
                    }
                }
            )
        },
        floatingActionButton = {
            if (uiState.dueCards.isNotEmpty()) {
                ExtendedFloatingActionButton(
                    onClick = { 
                        viewModel.startReviewSession()
                        // Navigate to flashcard screen
                    },
                    icon = { Icon(Icons.Default.School, null) },
                    text = { Text("Study ${uiState.dueCards.size} cards") }
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Search bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { 
                    searchQuery = it
                    viewModel.searchVocabulary(it)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                placeholder = { Text("Search vocabulary...") },
                leadingIcon = { Icon(Icons.Default.Search, null) },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = { 
                            searchQuery = ""
                            viewModel.searchVocabulary("")
                        }) {
                            Icon(Icons.Default.Close, "Clear")
                        }
                    }
                }
            )
            
            // Quick filters
            QuickFilterRow(
                selectedTopic = uiState.selectedTopic,
                selectedLevel = uiState.selectedLevel,
                onTopicClick = { topic ->
                    viewModel.filterByTopic(if (topic == uiState.selectedTopic) null else topic)
                },
                onLevelClick = { level ->
                    viewModel.filterByLevel(if (level == uiState.selectedLevel) null else level)
                },
                onFavoritesClick = { viewModel.getFavorites() }
            )
            
            // Vocabulary list
            if (uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            } else if (uiState.filteredList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No vocabulary found")
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(uiState.filteredList) { vocabulary ->
                        VocabularyCard(
                            vocabulary = vocabulary,
                            onClick = { 
                                navController.navigate(
                                    Screen.VocabularyDetail.createRoute(vocabulary.id)
                                )
                            },
                            onFavoriteClick = { viewModel.toggleFavorite(vocabulary.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun QuickFilterRow(
    selectedTopic: String?,
    selectedLevel: String?,
    onTopicClick: (String) -> Unit,
    onLevelClick: (String) -> Unit,
    onFavoritesClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(
            selected = selectedTopic == "Business",
            onClick = { onTopicClick("Business") },
            label = { Text("Business") }
        )
        FilterChip(
            selected = selectedTopic == "Travel",
            onClick = { onTopicClick("Travel") },
            label = { Text("Travel") }
        )
        FilterChip(
            selected = selectedLevel == "A2",
            onClick = { onLevelClick("A2") },
            label = { Text("A2") }
        )
        FilterChip(
            selected = false,
            onClick = onFavoritesClick,
            label = { Text("⭐ Favorites") }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VocabularyCard(
    vocabulary: Vocabulary,
    onClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = vocabulary.word,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    LevelBadge(level = vocabulary.level)
                }
                
                Text(
                    text = vocabulary.pronunciation,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.primary
                )
                
                Text(
                    text = vocabulary.definition,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2
                )
                
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Chip(text = vocabulary.category)
                }
            }
            
            IconButton(onClick = onFavoriteClick) {
                Icon(
                    imageVector = if (vocabulary.isFavorite) Icons.Default.Star else Icons.Default.StarBorder,
                    contentDescription = "Favorite",
                    tint = if (vocabulary.isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun Chip(
    text: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        color = MaterialTheme.colorScheme.secondaryContainer
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}
