package com.nghia.applen.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nghia.applen.data.repository.SocialRepository
import com.nghia.applen.model.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class SocialUiState(
    val isLoading: Boolean = false,
    val leaderboards: Map<LeaderboardType, Leaderboard> = emptyMap(),
    val friends: List<Friend> = emptyList(),
    val challenges: List<Challenge> = emptyList(),
    val selectedPeriod: LeaderboardPeriod = LeaderboardPeriod.WEEKLY,
    val selectedType: LeaderboardType = LeaderboardType.TOTAL_SCORE,
    val errorMessage: String? = null
)

class SocialViewModel(
    private val socialRepository: SocialRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SocialUiState())
    val uiState: StateFlow<SocialUiState> = _uiState.asStateFlow()
    
    init {
        loadSocialData()
    }
    
    fun loadSocialData() {
        loadLeaderboard(_uiState.value.selectedType, _uiState.value.selectedPeriod)
        loadFriends()
        loadChallenges()
    }
    
    fun loadLeaderboard(type: LeaderboardType, period: LeaderboardPeriod) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(
                isLoading = true,
                selectedType = type,
                selectedPeriod = period
            )
            
            socialRepository.getLeaderboard(type, period)
                .onSuccess { leaderboard ->
                    val updated = _uiState.value.leaderboards.toMutableMap()
                    updated[type] = leaderboard
                    
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        leaderboards = updated,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        isLoading = false,
                        errorMessage = error.message
                    )
                }
        }
    }
    
    fun loadFriends() {
        viewModelScope.launch {
            socialRepository.getFriends("current_user")
                .onSuccess { friends ->
                    _uiState.value = _uiState.value.copy(
                        friends = friends,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = error.message
                    )
                }
        }
    }
    
    fun loadChallenges() {
        viewModelScope.launch {
            socialRepository.getChallenges()
                .onSuccess { challenges ->
                    _uiState.value = _uiState.value.copy(
                        challenges = challenges,
                        errorMessage = null
                    )
                }
                .onFailure { error ->
                    _uiState.value = _uiState.value.copy(
                        errorMessage = error.message
                    )
                }
        }
    }
    
    fun sendFriendRequest(userId: String) {
        viewModelScope.launch {
            socialRepository.sendFriendRequest("current_user", userId, null)
                .onSuccess {
                    // Refresh friends list
                    loadFriends()
                }
        }
    }
    
    fun changePeriod(period: LeaderboardPeriod) {
        loadLeaderboard(_uiState.value.selectedType, period)
    }
    
    fun changeType(type: LeaderboardType) {
        loadLeaderboard(type, _uiState.value.selectedPeriod)
    }
}
