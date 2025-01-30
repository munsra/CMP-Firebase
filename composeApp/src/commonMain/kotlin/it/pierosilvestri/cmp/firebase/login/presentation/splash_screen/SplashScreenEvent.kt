package it.pierosilvestri.cmp.firebase.login.presentation.splash_screen

sealed class SplashScreenEvent {
    data object GoToHomeScreen : SplashScreenEvent()
    data object GoToLoginScreen : SplashScreenEvent()
}