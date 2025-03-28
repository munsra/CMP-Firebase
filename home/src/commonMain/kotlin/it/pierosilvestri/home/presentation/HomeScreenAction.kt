package it.pierosilvestri.home.presentation

sealed interface HomeScreenAction {
    object SignOut : HomeScreenAction
    object GoBack : HomeScreenAction
    object GoToNoteListScreen : HomeScreenAction
    object ClickOnActionAccount : HomeScreenAction
    object DismissActionAccount : HomeScreenAction
}