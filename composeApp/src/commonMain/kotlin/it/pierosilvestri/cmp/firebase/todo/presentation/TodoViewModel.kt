package it.pierosilvestri.cmp.firebase.todo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.cmp.firebase.home.presentation.HomeScreenEvent
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TodoViewModel(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _state = MutableStateFlow(TodoScreenState())
    val state = _state.asStateFlow()

    fun signOut(){
        _state.update {
            it.copy(
                isMenuExpanded = false
            )
        }
        viewModelScope.launch {
            authRepository.signOut()
        }
    }

    fun onActionAccountClick(){
        _state.update {
            it.copy(
                isMenuExpanded = true
            )
        }
    }

    fun onDismissActionAccount(){
        _state.update {
            it.copy(
                isMenuExpanded = false
            )
        }
    }

    fun addNote(){

    }
}