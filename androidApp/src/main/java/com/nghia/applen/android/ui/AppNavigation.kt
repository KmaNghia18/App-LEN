package com.nghia.applen.android.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nghia.applen.android.ui.screens.home.HomeScreen
import com.nghia.applen.android.ui.screens.splash.SplashScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Vocabulary : Screen("vocabulary")
    object VocabularyDetail : Screen("vocabulary/{wordId}") {
        fun createRoute(wordId: String) = "vocabulary/$wordId"
    }
    object Grammar : Screen("grammar")
    object GrammarDetail : Screen("grammar/{lessonId}") {
        fun createRoute(lessonId: String) = "grammar/$lessonId"
    }
    object Practice : Screen("practice")
    object Quiz : Screen("quiz/{quizId}") {
        fun createRoute(quizId: String) = "quiz/$quizId"
    }
    object Progress : Screen("progress")
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
        
        // TODO: Add other screens as they are implemented
    }
}
