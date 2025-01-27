package it.pierosilvestri.cmp.firebase.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import it.pierosilvestri.cmp.firebase.home.presentation.HomeScreenRoot
import it.pierosilvestri.cmp.firebase.login.data.repository.LoginRepositoryImpl
import it.pierosilvestri.cmp.firebase.login.data.repository.mock.MockLoginRepository
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenRoot
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController();
    NavHost(
        navController = navController,
        startDestination = Route.LoginScreen
    ) {
        composable<Route.LoginScreen>  {
            LoginScreenRoot(
                viewModel = LoginScreenViewModel(
                    loginRepository = LoginRepositoryImpl(
                        auth = Firebase.auth
                    )
                ),
                onLoginSuccess = {
                    navController.navigate(Route.HomeScreen)
                }
            )
        }
        composable<Route.HomeScreen> {
            HomeScreenRoot()
        }
    }
}