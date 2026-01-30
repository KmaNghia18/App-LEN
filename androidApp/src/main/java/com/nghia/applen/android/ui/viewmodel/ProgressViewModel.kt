package com.nghia.applen.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ProgressUiState(
    val isLoading: Boolean = false,
    val totalWords: Int = 0,
    val wordsThisWeek: Int = 0,
    val testsCompleted: Int = 0,
    val currentStreak: Int = 0,
    val totalStudyTimeMinutes: Int = 0,
    val studyTimeThisWeek: Int = 0,
    val averageScore: Int = 0,
    val studyData: List<StudyDayData> = emptyList(),
    val scoreData: List<ScoreData> = emptyList()
)

data class StudyDayData(
    val date: String,
    val minutes: Int
)

data class ScoreData(
    val quizName: String,
    val score: Int,
    val date: String
)

class ProgressViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(ProgressUiState())
    val uiState: StateFlow<ProgressUiState> = _uiState.asStateFlow()
    
    init {
        loadProgressData()
    }
    
    private fun loadProgressData() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            // Simulate loading mock data
            val mockData = generateMockProgressData()
            
            _uiState.value = _uiState.value.copy(
                isLoading = false,
                totalWords = mockData.totalWords,
                wordsThisWeek = mockData.wordsThisWeek,
                testsCompleted = mockData.testsCompleted,
                currentStreak = mockData.currentStreak,
                totalStudyTimeMinutes = mockData.totalStudyTimeMinutes,
                studyTimeThisWeek = mockData.studyTimeThisWeek,
                averageScore = mockData.averageScore,
                studyData = mockData.studyData,
                scoreData = mockData.scoreData
            )
        }
    }
    
    private fun generateMockProgressData(): ProgressUiState {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("MMM dd")
        
        // Generate last 7 days of study data
        val studyData = (6 down to 0).map { daysAgo ->
            val date = today.minusDays(daysAgo.toLong())
            StudyDayData(
                date = date.format(formatter),
                minutes = (10..60).random()
            )
        }
        
        // Generate recent quiz scores
        val scoreData = listOf(
            ScoreData("TOEIC Reading", 85, today.minusDays(1).format(formatter)),
            ScoreData("Grammar Test", 92, today.minusDays(3).format(formatter)),
            ScoreData("IELTS Reading", 78, today.minusDays(5).format(formatter)),
            ScoreData("Vocabulary Quiz", 88, today.minusDays(7).format(formatter))
        )
        
        return ProgressUiState(
            totalWords = 127,
            wordsThisWeek = 23,
            testsCompleted = 8,
            currentStreak = 5,
            totalStudyTimeMinutes = 347,
            studyTimeThisWeek = 142,
            averageScore = 86,
            studyData = studyData,
            scoreData = scoreData
        )
    }
    
    fun refreshData() {
        loadProgressData()
    }
}
