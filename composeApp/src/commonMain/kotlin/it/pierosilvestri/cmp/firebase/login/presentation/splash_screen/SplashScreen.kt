package it.pierosilvestri.cmp.firebase.login.presentation.splash_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import it.pierosilvestri.cmp.firebase.core_ui.presentation.components.LoadingPopup
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenEvent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SplashScreenRoot(
    viewModel: SplashScreenViewModel = koinViewModel(),
    onUserNotLogged: () -> Unit,
    onUserLogged: () -> Unit,
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {
            when (it) {
                is SplashScreenEvent.GoToHomeScreen -> onUserLogged()
                is SplashScreenEvent.GoToLoginScreen -> onUserNotLogged()
            }
        }
    }

    SplashScreen(
        state = state
    )

}

@Composable
fun SplashScreen(
    state: SplashScreenState,
) {

    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Splash Screen"
            )
            if(state.isLoading){
                LoadingPopup()
            }
        }
    }

}