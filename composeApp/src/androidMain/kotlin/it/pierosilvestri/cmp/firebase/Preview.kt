package it.pierosilvestri.cmp.firebase

import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import it.pierosilvestri.core_ui.presentation.theme.AppTheme
import it.pierosilvestri.login.presentation.signup_screen.SignUpScreenState

@Composable
@Preview
fun PreviewLoginScreen() {
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