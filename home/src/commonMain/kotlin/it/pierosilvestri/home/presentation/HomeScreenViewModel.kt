package it.pierosilvestri.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.auth.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<HomeScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun signOut(){
        _state.update {
            it.copy(
                isMenuExpanded = false
            )
        }
        viewModelScope.launch {
            authRepository.signOut()
            _uiEvent.send(HomeScreenEvent.GoBackToLoginScreen)
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

    fun goToTodoListScreen(){
        viewModelScope.launch {
            _uiEvent.send(HomeScreenEvent.GoToTodoListScreen)
        }
    }
}