package it.pierosilvestri.cmp.firebase.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.cmp.firebase.login.domain.LoginRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(
    private val loginRepository: LoginRepository,
): ViewModel() {

    private val _uiEvent = Channel<HomeScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun signOut(){
        viewModelScope.launch {
            loginRepository.signOut()
            _uiEvent.send(HomeScreenEvent.GoBackToLoginScreen)
        }
    }

}