package com.nghia.applen.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nghia.applen.android.ui.screens.home.HomeScreen
import com.nghia.applen.android.ui.screens.splash.SplashScreen
import com.nghia.applen.android.ui.screens.vocabulary.VocabularyListScreen
import com.nghia.applen.android.ui.screens.vocabulary.FlashCardScreen
import com.nghia.applen.android.ui.screens.grammar.GrammarListScreen
import com.nghia.applen.android.ui.screens.grammar.GrammarDetailScreen
import com.nghia.applen.android.ui.screens.quiz.QuizListScreen
import com.nghia.applen.android.ui.screens.quiz.QuizPlayerScreen
import com.nghia.applen.android.ui.screens.settings.SettingsScreen
import com.nghia.applen.android.ui.screens.progress.ProgressScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Vocabulary : Screen("vocabulary")
    object FlashCard : Screen("flashcard")
    object VocabularyDetail : Screen("vocabulary/{wordId}") {
        fun createRoute(wordId: String) = "vocabulary/$wordId"
    }
    object Grammar : Screen("grammar")
    object GrammarDetail : Screen("grammar/{lessonId}") {
        fun createRoute(lessonId: String) = "grammar/$lessonId"
    }
    object QuizList : Screen("quiz")
    object QuizPlayer : Screen("quiz_player")
    object Progress : Screen("progress")
    object Settings : Screen("settings")
    object Profile : Screen("profile")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        
        composable(Screen.Vocabulary.route) {
            VocabularyListScreen(navController = navController)
        }
        
        composable(Screen.FlashCard.route) {
            FlashCardScreen(navController = navController)
        }
        
        composable(Screen.Grammar.route) {
            GrammarListScreen(navController = navController)
        }
        
        composable(Screen.GrammarDetail.route) { backStackEntry ->
            val lessonId = backStackEntry.arguments?.getString("lessonId") ?: return@composable
            GrammarDetailScreen(
                grammarId = lessonId,
                navController = navController
            )
        }
        
        composable(Screen.QuizList.route) {
            QuizListScreen(navController = navController)
        }
        
        composable(Screen.QuizPlayer.route) {
            QuizPlayerScreen(navController = navController)
        }
        
        composable(Screen.Settings.route) {
            SettingsScreen(navController = navController)
        }
        
        composable(Screen.Progress.route) {
            ProgressScreen(navController = navController)
        }
        
        // TODO: Add Profile screen
    }
}
