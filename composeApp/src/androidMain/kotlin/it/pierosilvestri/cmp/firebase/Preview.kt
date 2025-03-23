package it.pierosilvestri.cmp.firebase

import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.pierosilvestri.core_ui.presentation.theme.AppTheme
import it.pierosilvestri.login.presentation.login_screen.LoginScreen
import it.pierosilvestri.login.presentation.login_screen.LoginScreenState
import it.pierosilvestri.login.presentation.signup_screen.SignUpScreenState


@Preview
@Composable
fun PreviewLoginScreen() {
    AppTheme(
        dynamicColor = false
    ) {
        LoginScreen(
            state = LoginScreenState(),
            onAction = {}
        )
    }

}