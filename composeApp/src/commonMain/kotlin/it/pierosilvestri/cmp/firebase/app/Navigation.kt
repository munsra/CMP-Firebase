package it.pierosilvestri.cmp.firebase.app

import SignUpScreenRoot
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import it.pierosilvestri.todo.presentation.TodoScreenRoot
import it.pierosilvestri.home.presentation.HomeScreenRoot
import it.pierosilvestri.login.presentation.login_screen.LoginScreenRoot
import it.pierosilvestri.login.presentation.splash_screen.SplashScreenRoot

@Composable
fun Navigation() {
    val navController = rememberNavController();
    NavHost(
        navController = navController,
        startDestination = Route.SplashScreen
    ) {

        composable<Route.SplashScreen> {
            SplashScreenRoot(
                onUserNotLogged = {
                    navController.navigate(Route.LoginScreen) {
                        popUpTo(Route.SplashScreen) { inclusive = true }
                    }
                },
                onUserLogged = {
                    navController.navigate(Route.HomeScreen) {
                        popUpTo(Route.SplashScreen) { inclusive = true }
                    }
                }
            )
        }

        composable<Route.LoginScreen>  {
            LoginScreenRoot(
                onLoginSuccess = {
                    navController.navigate(Route.HomeScreen) {
                        popUpTo(Route.LoginScreen) { inclusive = true }
                    }
                },
                onSignUp = {
                    navController.navigate(Route.SignUpScreen)
                }
            )
        }
        composable<Route.SignUpScreen> {
            SignUpScreenRoot(
                goToLoginScreen = {
                    navController.navigate(Route.LoginScreen) {
                        popUpTo(Route.SignUpScreen) { inclusive = true }
                    }
                }
            )
        }
        composable<Route.HomeScreen> {
            HomeScreenRoot(
                goBack = {
                    navController.navigate(Route.LoginScreen) {
                        popUpTo(Route.HomeScreen) { inclusive = true }
                    }
                },
                goToNextPage = {
                    navController.navigate(Route.TodoScreen) {
                        popUpTo(Route.HomeScreen) { inclusive = true }
                    }
                }
            )
        }

        composable<Route.TodoScreen> {
            TodoScreenRoot(
                goBack = {
                    navController.navigate(Route.HomeScreen) {
                        popUpTo(Route.TodoScreen) { inclusive = true }
                    }
                }
            )
        }
    }
}