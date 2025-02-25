package it.pierosilvestri.login.presentation.signup_screen

import it.pierosilvestri.core.presentation.UiText

data class SignUpScreenState(
    val name: String? = "test1",
    val email: String? = "munsra@gmail.com",
    val password: String? = "n7(BC08BX(o9",
    val isLoading: Boolean = false,
    val errorMessage: UiText? = null,
    val signUpSuccess: Boolean = false,
    val errorName: UiText? = null,
    val errorEmail: UiText? = null,
    val errorPassword: UiText? = null
)
