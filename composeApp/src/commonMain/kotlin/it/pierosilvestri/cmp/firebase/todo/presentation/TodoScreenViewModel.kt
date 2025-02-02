package it.pierosilvestri.cmp.firebase.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.cmp.firebase.core.domain.models.Note
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import it.pierosilvestri.cmp.firebase.todo.domain.repository.TodoRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoScreenViewModel(
    private val authRepository: AuthRepository,
    private val todoRepository: TodoRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TodoScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<TodoScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private val currentUserId = authRepository.currentUserId

    init {
        fetchNotes()
    }

    fun signOut() {
        _state.update {
            it.copy(
                isMenuExpanded = false
            )
        }
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    fun onActionAccountClick() {
        _state.update {
            it.copy(
                isMenuExpanded = true
            )
        }
    }

    fun onDismissActionAccount() {
        _state.update {
            it.copy(
                isMenuExpanded = false
            )
        }
    }

    fun fetchNotes() {
        viewModelScope.launch {
            todoRepository.getNotesForUser(currentUserId).collect { notes ->
                _state.update {
                    it.copy(
                        notes = notes
                    )
                }
            }
        }
    }

    fun addNote() {

    }
}