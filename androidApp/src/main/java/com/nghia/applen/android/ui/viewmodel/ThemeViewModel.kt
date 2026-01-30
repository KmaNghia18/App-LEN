package com.nghia.applen.android.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import com.nghia.applen.android.ui.theme.ThemeMode

data class ThemeUiState(
    val themeMode: ThemeMode = ThemeMode.SYSTEM,
    val dynamicColor: Boolean = true
)

class ThemeViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(ThemeUiState())
    val uiState: StateFlow<ThemeUiState> = _uiState.asStateFlow()
    
    fun setThemeMode(mode: ThemeMode) {
        _uiState.value = _uiState.value.copy(themeMode = mode)
        // TODO: Save to SharedPreferences
    }
    
    fun toggleDynamicColor() {
        _uiState.value = _uiState.value.copy(
            dynamicColor = !_uiState.value.dynamicColor
        )
        // TODO: Save to SharedPreferences
    }
}
