package it.pierosilvestri.cmp.firebase.login.presentation.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.login.domain.AuthError
import it.pierosilvestri.cmp.firebase.login.domain.ValidationError
import it.pierosilvestri.cmp.firebase.login.domain.toUiText
import it.pierosilvestri.cmp.firebase.login.domain.use_case.LoginUserUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginScreenViewModel(
    private val loginUserUseCase: LoginUserUseCase
): ViewModel() {

    private val _state = MutableStateFlow(LoginScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<LoginScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEmailChange(email: String) {
        _state.value = _state.value.copy(
            email = email,
            errorEmail = null
        )
    }

    fun onPasswordChange(password: String) {
        _state.value = _state.value.copy(
            password = password,
            errorPassword = null
        )
    }

    fun onLogin() {
        _state.value = _state.value.copy(
            isLoading = true,
            errorMessage = null,
            errorEmail = null,
            errorPassword = null
        )
        viewModelScope.launch {
            when (val result = loginUserUseCase(_state.value.email, _state.value.password)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(isLoading = false)
                    _uiEvent.send(LoginScreenEvent.GoToHomeScreen)
                }
                is Result.Error -> {
                    when(result.error) {
                        is AuthError -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorMessage = result.error.toUiText()
                            )
                        }
                        is ValidationError.Email -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorEmail = result.error.toUiText()
                            )
                        }
                        is ValidationError.Password -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorPassword = result.error.toUiText()
                            )
                        }
                    }
                }
            }
        }
    }

    fun onSignUp() {
        viewModelScope.launch {
            _uiEvent.send(LoginScreenEvent.GoToSignUpScreen)
        }
    }

    fun onLoginWithGoogle() {
        TODO()
    }

    fun onDismissError() {
        _state.value = _state.value.copy(errorMessage = null)
    }

    fun onTogglePasswordVisibility() {
        _state.value = _state.value.copy(passwordVisible = !_state.value.passwordVisible)
    }

}