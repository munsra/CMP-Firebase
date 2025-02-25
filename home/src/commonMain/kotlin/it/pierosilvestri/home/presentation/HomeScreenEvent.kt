package it.pierosilvestri.home.presentation

sealed class HomeScreenEvent {
    data object GoBackToLoginScreen : HomeScreenEvent()
    data object GoToTodoListScreen: HomeScreenEvent()
}