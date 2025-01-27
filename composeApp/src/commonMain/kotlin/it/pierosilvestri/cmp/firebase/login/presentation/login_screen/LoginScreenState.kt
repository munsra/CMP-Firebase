package it.pierosilvestri.cmp.firebase.login.presentation.login_screen

import it.pierosilvestri.cmp.firebase.core.presentation.UiText

data class LoginScreenState(
    val email: String? = "munsra@gmail.com",
    val password: String? = "123456789",
    val passwordVisible: Boolean = false,
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val errorMessage: UiText? = null
)
