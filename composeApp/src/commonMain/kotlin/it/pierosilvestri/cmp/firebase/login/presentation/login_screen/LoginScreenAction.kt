package it.pierosilvestri.cmp.firebase.login.presentation.login_screen


sealed interface LoginScreenAction {
    data class EmailChanged(val email: String) : LoginScreenAction
    data class PasswordChanged(val password: String) : LoginScreenAction
    object TogglePasswordVisibility : LoginScreenAction
    object Login : LoginScreenAction
    object SignUp : LoginScreenAction
    object DismissError: LoginScreenAction
    object LoginWithGoogle : LoginScreenAction
}