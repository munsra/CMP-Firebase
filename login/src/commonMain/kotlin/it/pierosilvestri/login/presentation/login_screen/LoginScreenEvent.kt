package it.pierosilvestri.login.presentation.login_screen

sealed class LoginScreenEvent {
    data object GoToHomeScreen : LoginScreenEvent()
    data object GoToSignUpScreen : LoginScreenEvent()
}