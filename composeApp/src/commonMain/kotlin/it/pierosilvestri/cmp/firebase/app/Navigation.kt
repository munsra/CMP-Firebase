package it.pierosilvestri.cmp.firebase.app

import SignUpScreenRoot
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import it.pierosilvestri.cmp.firebase.home.presentation.HomeScreenRoot
import it.pierosilvestri.cmp.firebase.home.presentation.HomeScreenViewModel
import it.pierosilvestri.cmp.firebase.login.data.repository.LoginRepositoryImpl
import it.pierosilvestri.cmp.firebase.login.data.repository.mock.MockLoginRepository
import it.pierosilvestri.cmp.firebase.login.data.services.AuthServiceImpl
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenRoot
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController();
    NavHost(
        navController = navController,
        startDestination = Route.LoginScreen
    ) {

        composable<Route.LoginScreen>  {
            val viewModel = koinViewModel<LoginScreenViewModel>()
            LoginScreenRoot(
                viewModel = viewModel,
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
                onSignUpSuccess = {
                    navController.navigate(Route.LoginScreen)
                }
            )
        }
        composable<Route.HomeScreen> {
            HomeScreenRoot(
                viewModel = koinViewModel<HomeScreenViewModel>(),
                onLogout = {
                    navController.navigate(Route.LoginScreen) {
                        popUpTo(Route.HomeScreen) { inclusive = true }
                    }
                }
            )
        }
    }
}