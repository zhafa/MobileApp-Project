package com.example.mobileapptechnobit

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mobileapptechnobit.ui.LoginScreen
import com.example.mobileapptechnobit.ui.WalkthroughScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(navController)
        }
        composable(Screen.Walkthrough.route) {
            WalkthroughScreen(navController)
        }
        composable(Screen.Login.route) {
            LoginScreen()
        }
    }
}

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")
    object Walkthrough : Screen("walkthrough_screen")
    object Login : Screen("login_screen") // Tambahkan route untuk login
}
