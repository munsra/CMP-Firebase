package it.pierosilvestri.cmp.firebase.login.presentation.signup_screen


sealed interface SignUpScreenEvent {
    data object GotoLoginScreen : SignUpScreenEvent
}