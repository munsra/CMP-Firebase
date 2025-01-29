package it.pierosilvestri.cmp.firebase.home.presentation

sealed interface HomeScreenAction {
    object SignOut : HomeScreenAction
    object GoBack : HomeScreenAction
}