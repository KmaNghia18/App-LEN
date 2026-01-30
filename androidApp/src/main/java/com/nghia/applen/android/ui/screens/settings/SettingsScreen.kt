package com.nghia.applen.android.ui.screens.settings

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    navController: NavController,
    themeViewModel: com.nghia.applen.android.ui.viewmodel.ThemeViewModel = org.koin.androidx.compose.koinViewModel()
) {
    val themeState by themeViewModel.uiState.collectAsStateWithLifecycle()
    
    val selectedTheme = when (themeState.themeMode) {
        com.nghia.applen.android.ui.theme.ThemeMode.LIGHT -> "Light"
        com.nghia.applen.android.ui.theme.ThemeMode.DARK -> "Dark"
        com.nghia.applen.android.ui.theme.ThemeMode.SYSTEM -> "System"
    }
    
    var notificationsEnabled by remember { mutableStateOf(true) }
    var dailyReminder by remember { mutableStateOf(true) }
    var streakReminder by remember { mutableStateOf(true) }
    var autoPlayAudio by remember { mutableStateOf(false) }
    
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Settings") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Appearance Section
            item {
                SettingsSectionHeader(title = "Appearance")
            }
            
            item {
                ThemeSelector(
                    selectedTheme = selectedTheme,
                    onThemeSelected = { theme ->
                        val mode = when (theme) {
                            "Light" -> com.nghia.applen.android.ui.theme.ThemeMode.LIGHT
                            "Dark" -> com.nghia.applen.android.ui.theme.ThemeMode.DARK
                            else -> com.nghia.applen.android.ui.theme.ThemeMode.SYSTEM
                        }
                        themeViewModel.setThemeMode(mode)
                    }
                )
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
            
            // Notifications Section
            item {
                SettingsSectionHeader(title = "Notifications")
            }
            
            item {
                SettingsSwitch(
                    title = "Enable Notifications",
                    description = "Receive study reminders and updates",
                    icon = Icons.Default.Notifications,
                    checked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }
            
            item {
                SettingsSwitch(
                    title = "Daily Study Reminder",
                    description = "Get reminded to practice every day",
                    icon = Icons.Default.Schedule,
                    checked = dailyReminder,
                    onCheckedChange = { dailyReminder = it },
                    enabled = notificationsEnabled
                )
            }
            
            item {
                SettingsSwitch(
                    title = "Streak Reminder",
                    description = "Don't lose your learning streak",
                    icon = Icons.Default.LocalFireDepartment,
                    checked = streakReminder,
                    onCheckedChange = { streakReminder = it },
                    enabled = notificationsEnabled
                )
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
            
            // Learning Preferences
            item {
                SettingsSectionHeader(title = "Learning")
            }
            
            item {
                SettingsSwitch(
                    title = "Auto-play Audio",
                    description = "Automatically play word pronunciation",
                    icon = Icons.Default.VolumeUp,
                    checked = autoPlayAudio,
                    onCheckedChange = { autoPlayAudio = it }
                )
            }
            
            item { Spacer(modifier = Modifier.height(16.dp)) }
            
            // About Section
            item {
                SettingsSectionHeader(title = "About")
            }
            
            item {
                SettingsItem(
                    title = "Version",
                    description = "1.0.0 (Build 1)",
                    icon = Icons.Default.Info,
                    onClick = {}
                )
            }
            
            item {
                SettingsItem(
                    title = "Privacy Policy",
                    description = "View our privacy policy",
                    icon = Icons.Default.Policy,
                    onClick = {}
                )
            }
            
            item {
                SettingsItem(
                    title = "Rate App",
                    description = "Share your feedback",
                    icon = Icons.Default.Star,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
private fun SettingsSectionHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun ThemeSelector(
    selectedTheme: String,
    onThemeSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                Icon(
                    Icons.Default.Palette,
                    contentDescription = null,
                    modifier = Modifier.size(24.dp),
                    tint = MaterialTheme.colorScheme.primary
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "Theme",
                    style = MaterialTheme.typography.titleMedium
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ThemeChip(
                    label = "Light",
                    icon = Icons.Default.LightMode,
                    selected = selectedTheme == "Light",
                    onClick = { onThemeSelected("Light") },
                    modifier = Modifier.weight(1f)
                )
                
                ThemeChip(
                    label = "Dark",
                    icon = Icons.Default.DarkMode,
                    selected = selectedTheme == "Dark",
                    onClick = { onThemeSelected("Dark") },
                    modifier = Modifier.weight(1f)
                )
                
                ThemeChip(
                    label = "System",
                    icon = Icons.Default.SettingsBrightness,
                    selected = selectedTheme == "System",
                    onClick = { onThemeSelected("System") },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ThemeChip(
    label: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = selected,
        onClick = onClick,
        label = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Icon(
                    icon,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(label, style = MaterialTheme.typography.labelSmall)
            }
        },
        modifier = modifier
    )
}

@Composable
private fun SettingsSwitch(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (enabled)
                MaterialTheme.colorScheme.surface
            else
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = if (enabled)
                    MaterialTheme.colorScheme.primary
                else
                    MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = if (enabled)
                        MaterialTheme.colorScheme.onSurface
                    else
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = if (enabled)
                        MaterialTheme.colorScheme.onSurfaceVariant
                    else
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
            
            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                enabled = enabled
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SettingsItem(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit,
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
            Icon(
                icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            
            Icon(
                Icons.Default.ChevronRight,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
