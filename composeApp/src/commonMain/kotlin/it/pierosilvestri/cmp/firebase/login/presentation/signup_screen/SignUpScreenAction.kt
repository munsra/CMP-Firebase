package it.pierosilvestri.cmp.firebase.login.presentation.signup_screen

sealed interface SignUpScreenAction {
    data class NameChanged(val name: String) : SignUpScreenAction
    data class EmailChanged(val email: String) : SignUpScreenAction
    data class PasswordChanged(val password: String) : SignUpScreenAction
    object DismissError: SignUpScreenAction
    object DismissSignUpSuccess: SignUpScreenAction
    object GoToLoginScreen: SignUpScreenAction
    object SignUp : SignUpScreenAction
}