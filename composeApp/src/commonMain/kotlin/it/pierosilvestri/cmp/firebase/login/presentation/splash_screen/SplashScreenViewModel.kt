package it.pierosilvestri.cmp.firebase.login.presentation.splash_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val authRepository: AuthRepository
): ViewModel() {

    private val _state = MutableStateFlow(SplashScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<SplashScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        loadUserInfo()
    }

    private fun loadUserInfo(){
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            authRepository.currentUser.collect {
                _state.update {
                    it.copy(
                        isLoading = false,
                    )
                }
                println("UTENTE: ${it.toString()}")
                if(it != null) {
                    _uiEvent.trySend(SplashScreenEvent.GoToHomeScreen)
                }else {
                    _uiEvent.trySend(SplashScreenEvent.GoToLoginScreen)
                }
            }
        }
    }
}