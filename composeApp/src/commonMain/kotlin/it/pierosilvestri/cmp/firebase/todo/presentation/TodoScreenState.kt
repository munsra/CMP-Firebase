package it.pierosilvestri.cmp.firebase.todo.presentation

import it.pierosilvestri.cmp.firebase.core.domain.models.Note

data class TodoScreenState(
    val isLoading: Boolean = false,
    val isMenuExpanded: Boolean = false,
    val notes: List<Note> = emptyList()
)
