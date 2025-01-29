package it.pierosilvestri.cmp.firebase.login.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.core.domain.onError
import it.pierosilvestri.cmp.firebase.core.domain.onSuccess
import it.pierosilvestri.cmp.firebase.login.domain.LoginError
import it.pierosilvestri.cmp.firebase.login.domain.LoginRepository
import it.pierosilvestri.cmp.firebase.login.domain.toUiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginRepository: LoginRepository
): ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<LoginScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser = _currentUser.asStateFlow()

    init {
        println("LoginViewModel has been launched")
        viewModelScope.launch {
            loginRepository.currentUser.collect {
                _currentUser.value = it
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun onLogin() {
        if(_state.value.email.isNullOrBlank()  || _state.value.password.isNullOrBlank()) {
            _state.value = _state.value.copy(
                errorMessage = LoginError.SignIn.EmptyFields.toUiText()
            )
            return
        }

        _state.value = _state.value.copy(isLoading = true)
        viewModelScope.launch {
            loginRepository.login(
                email = _state.value.email ?: "",
                password = _state.value.password ?: ""
            ).onSuccess {
                _state.value = _state.value.copy(isLoading = false)
                _uiEvent.send(LoginScreenEvent.GoToHomeScreen)
            }.onError {
                _state.value = _state.value.copy(
                    isLoading = false,
                    errorMessage = it.toUiText()
                )
            }
        }
    }

    fun onSignUp() {
        viewModelScope.launch {
            _uiEvent.send(LoginScreenEvent.GoToSignUpScreen)
        }
    }

    fun onLoginWithGoogle() {
        viewModelScope.launch {

        }
    }

    fun onDismissError() {
        _state.value = _state.value.copy(errorMessage = null)
    }

    fun onTogglePasswordVisibility() {
        _state.value = _state.value.copy(passwordVisible = !_state.value.passwordVisible)
    }

}