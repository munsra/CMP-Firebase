package it.pierosilvestri.login.presentation.signup_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import it.pierosilvestri.auth.domain.AuthError
import it.pierosilvestri.auth.domain.ValidationError
import it.pierosilvestri.core.domain.Result
import it.pierosilvestri.login.domain.toUiText
import it.pierosilvestri.login.domain.use_case.SignUpUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpScreenViewModel(
    private val signupUseCase: SignUpUseCase,
): ViewModel() {

    private val _state = MutableStateFlow(SignUpScreenState())
    val state = _state.asStateFlow()

    private val _uiEvent = Channel<SignUpScreenEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onNameChange(name: String) {
        _state.value = _state.value.copy(
            name = name,
            errorName = null
        )
    }

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

    fun onSignUp() {

        _state.value = _state.value.copy(
            isLoading = true,
            errorMessage = null,
            errorEmail = null,
            errorPassword = null,
            errorName = null
        )

        viewModelScope.launch {
            when(val result = signupUseCase(_state.value.email, _state.value.password)) {
                is Result.Success -> {
                    _state.value = _state.value.copy(
                        isLoading = false,
                        signUpSuccess = true
                    )
                }
                is Result.Error -> {
                    when(result.error) {
                        is AuthError -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorMessage = (result.error as AuthError).toUiText()
                            )
                        }
                        is ValidationError.Email -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorEmail = (result.error as ValidationError.Email).toUiText()
                            )
                        }
                        is ValidationError.Password -> {
                            _state.value = _state.value.copy(
                                isLoading = false,
                                errorPassword = (result.error as ValidationError.Password).toUiText()
                            )
                        }
                    }
                }
            }
        }
    }

    fun onDismissError() {
        _state.value = _state.value.copy(errorMessage = null)
    }

    fun onDismissignUpSuccess() {
        _state.value = _state.value.copy(signUpSuccess = false)
        goToLoginScreen()
    }

    fun goToLoginScreen(){
        _uiEvent.trySend(SignUpScreenEvent.GotoLoginScreen)
    }
}