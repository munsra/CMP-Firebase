package it.pierosilvestri.todo.presentation

import it.pierosilvestri.todo.domain.models.Note

data class TodoScreenState(
    val isLoading: Boolean = false,
    val isMenuExpanded: Boolean = false,
    val notes: List<Note> = emptyList(),
    val isAddingNoteDialogVisible: Boolean = false
)
