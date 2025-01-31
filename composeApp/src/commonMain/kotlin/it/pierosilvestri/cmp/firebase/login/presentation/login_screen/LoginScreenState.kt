package it.pierosilvestri.cmp.firebase.login.presentation.login_screen

import it.pierosilvestri.cmp.firebase.core.presentation.UiText

data class LoginScreenState(
    val email: String? = "test2@gmail.com",
    val password: String? = "1234567",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: UiText? = null,
    val errorEmail: UiText? = null,
    val errorPassword: UiText? = null
)
