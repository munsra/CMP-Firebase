package it.pierosilvestri.cmp.firebase.home.presentation

sealed class HomeScreenEvent {
    data object GoBackToLoginScreen : HomeScreenEvent()
}