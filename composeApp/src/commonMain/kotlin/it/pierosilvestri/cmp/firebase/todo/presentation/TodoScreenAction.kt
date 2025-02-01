package it.pierosilvestri.cmp.firebase.todo.presentation

sealed interface TodoScreenAction {
    object SignOut : TodoScreenAction
    object GoBack : TodoScreenAction
    object ClickOnActionAccount : TodoScreenAction
    object DismissActionAccount : TodoScreenAction
    object AddNote: TodoScreenAction
}