package it.pierosilvestri.login.presentation.signup_screen

sealed interface SignUpScreenEvent {
    data object GotoLoginScreen : SignUpScreenEvent
}