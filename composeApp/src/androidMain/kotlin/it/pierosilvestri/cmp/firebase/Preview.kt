package it.pierosilvestri.cmp.firebase

import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.pierosilvestri.cmp.firebase.core.presentation.UiText
import it.pierosilvestri.cmp.firebase.core_ui.theme.AppTheme
import it.pierosilvestri.cmp.firebase.home.presentation.HomeScreen
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreen
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenState
import it.pierosilvestri.cmp.firebase.login.presentation.signup_screen.SignUpScreenState

@Composable
@Preview
fun PreviewLoginScreen(modifier: Modifier = Modifier) {
    AppTheme(
        dynamicColor = false
    ) {
        SignUpScreen(
            state = SignUpScreenState(
            ),
            onAction = {}
        )
    }

}