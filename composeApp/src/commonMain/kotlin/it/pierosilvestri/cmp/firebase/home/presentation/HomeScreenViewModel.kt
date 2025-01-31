package it.pierosilvestri.cmp.firebase.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val authRepository: AuthRepository,
): ViewModel() {

    private val _uiEvent = Channel<HomeScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun signOut(){
        viewModelScope.launch {
            authRepository.signOut()
            _uiEvent.send(HomeScreenEvent.GoBackToLoginScreen)
        }
    }

}