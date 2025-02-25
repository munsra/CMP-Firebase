package it.pierosilvestri.todo.presentation

import it.pierosilvestri.todo.domain.models.Note

sealed interface TodoScreenAction {
    object SignOut : TodoScreenAction
    object GoBack : TodoScreenAction
    object ClickOnActionAccount : TodoScreenAction
    object DismissActionAccount : TodoScreenAction
    object DismissAddNoteDialog : TodoScreenAction
    object OpenNewNoteDialog: TodoScreenAction
    data class SaveNote(val title: String?, val content: String) : TodoScreenAction
    data class OnNoteClick(val note: Note) : TodoScreenAction
}