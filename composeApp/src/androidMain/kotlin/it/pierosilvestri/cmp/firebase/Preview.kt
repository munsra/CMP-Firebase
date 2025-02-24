package it.pierosilvestri.cmp.firebase

import SignUpScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import it.pierosilvestri.cmp.firebase.login.presentation.signup_screen.SignUpScreenState
import it.pierosilvestri.core_ui.presentation.theme.AppTheme

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