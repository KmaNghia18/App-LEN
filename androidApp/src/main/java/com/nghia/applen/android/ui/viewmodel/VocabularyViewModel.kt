package com.nghia.applen.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nghia.applen.data.repository.VocabularyRepository
import com.nghia.applen.model.Vocabulary
import com.nghia.applen.model.VocabularyLevel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class VocabularyUiState(
    val vocabularyList: List<Vocabulary> = emptyList(),
    val dueCards: List<Vocabulary> = emptyList(),
    val filteredList: List<Vocabulary> = emptyList(),
    val selectedTopic: String? = null,
    val selectedLevel: VocabularyLevel? = null,
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentCard: Vocabulary? = null,
    val reviewProgress: ReviewProgress = ReviewProgress()
)

data class ReviewProgress(
    val totalCards: Int = 0,
    val completedCards: Int = 0,
    val correctCount: Int = 0,
    val incorrectCount: Int = 0
)

class VocabularyViewModel(
    private val repository: VocabularyRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(VocabularyUiState())
    val uiState: StateFlow<VocabularyUiState> = _uiState.asStateFlow()
    
    init {
        loadVocabulary()
        loadDueCards()
    }
    
    private fun loadVocabulary() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            
            repository.getAllVocabulary()
                .catch { error ->
                    _uiState.update { 
                        it.copy(
                            isLoading = false, 
                            error = error.message ?: "Unknown error"
                        ) 
                    }
                }
                .collect { vocabularyList ->
                    _uiState.update { 
                        it.copy(
                            vocabularyList = vocabularyList,
                            filteredList = vocabularyList,
                            isLoading = false,
                            error = null
                        ) 
                    }
                }
        }
    }
    
    private fun loadDueCards() {
        viewModelScope.launch {
            repository.getDueCards()
                .catch { error ->
                    _uiState.update { 
                        it.copy(error = error.message ?: "Failed to load due cards") 
                    }
                }
                .collect { dueCards ->
                    _uiState.update { 
                        it.copy(
                            dueCards = dueCards,
                            reviewProgress = it.reviewProgress.copy(totalCards = dueCards.size)
                        ) 
                    }
                }
        }
    }
    
    fun filterByTopic(topic: String?) {
        _uiState.update { it.copy(selectedTopic = topic) }
        applyFilters()
    }
    
    fun filterByLevel(level: VocabularyLevel?) {
        _uiState.update { it.copy(selectedLevel = level) }
        applyFilters()
    }
    
    fun searchVocabulary(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
        
        if (query.isBlank()) {
            applyFilters()
            return
        }
        
        viewModelScope.launch {
            repository.searchByWord(query)
                .catch { error ->
                    _uiState.update { 
                        it.copy(error = error.message ?: "Search failed") 
                    }
                }
                .collect { results ->
                    _uiState.update { it.copy(filteredList = results) }
                }
        }
    }
    
    fun toggleFavorite(id: String) {
        viewModelScope.launch {
            repository.toggleFavorite(id)
        }
    }
    
    fun startReviewSession() {
        val dueCards = _uiState.value.dueCards
        if (dueCards.isEmpty()) return
        
        _uiState.update { 
            it.copy(
                currentCard = dueCards.first(),
                reviewProgress = ReviewProgress(
                    totalCards = dueCards.size,
                    completedCards = 0,
                    correctCount = 0,
                    incorrectCount = 0
                )
            ) 
        }
    }
    
    fun reviewCard(knows: Boolean) {
        val currentCard = _uiState.value.currentCard ?: return
        val currentProgress = _uiState.value.reviewProgress
        
        viewModelScope.launch {
            // Update mastery in repository
            repository.reviewCard(currentCard.id, knows)
            
            // Update UI state
            val newCompleted = currentProgress.completedCards + 1
            val newCorrect = if (knows) currentProgress.correctCount + 1 else currentProgress.correctCount
            val newIncorrect = if (!knows) currentProgress.incorrectCount + 1 else currentProgress.incorrectCount
            
            val remainingCards = _uiState.value.dueCards.drop(newCompleted)
            
            _uiState.update { 
                it.copy(
                    currentCard = remainingCards.firstOrNull(),
                    reviewProgress = currentProgress.copy(
                        completedCards = newCompleted,
                        correctCount = newCorrect,
                        incorrectCount = newIncorrect
                    )
                ) 
            }
        }
    }
    
    fun endReviewSession() {
        _uiState.update { 
            it.copy(
                currentCard = null,
                reviewProgress = ReviewProgress()
            ) 
        }
    }
    
    private fun applyFilters() {
        viewModelScope.launch {
            val topic = _uiState.value.selectedTopic
            val level = _uiState.value.selectedLevel
            
            when {
                topic != null -> {
                    repository.getByTopic(topic)
                        .collect { words ->
                            _uiState.update { 
                                it.copy(
                                    filteredList = if (level != null) {
                                        words.filter { it.level == level }
                                    } else {
                                        words
                                    }
                                ) 
                            }
                        }
                }
                level != null -> {
                    repository.getByLevel(level.name)
                        .collect { words ->
                            _uiState.update { it.copy(filteredList = words) }
                        }
                }
                else -> {
                    _uiState.update { 
                        it.copy(filteredList = it.vocabularyList) 
                    }
                }
            }
        }
    }
    
    fun getFavorites() {
        viewModelScope.launch {
            repository.getFavorites()
                .collect { favorites ->
                    _uiState.update { it.copy(filteredList = favorites) }
                }
        }
    }
}
