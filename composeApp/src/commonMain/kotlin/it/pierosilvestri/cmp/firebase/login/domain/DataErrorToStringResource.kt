package it.pierosilvestri.cmp.firebase.login.domain

import cmp_firebase.composeapp.generated.resources.Res
import cmp_firebase.composeapp.generated.resources.*
import it.pierosilvestri.cmp.firebase.core.presentation.UiText


fun LoginError.toUiText(): UiText {
    val stringRes = when(this) {
        LoginError.SignIn.InvalidEmail -> Res.string.error_invalid_email
        LoginError.SignIn.UserNotFound -> Res.string.error_user_not_found
        LoginError.SignIn.WrongPassword -> Res.string.error_wrong_password
        LoginError.SignIn.UserDisabled -> Res.string.error_user_disabled
        LoginError.SignIn.NetworkError -> Res.string.error_network_error
        LoginError.SignIn.TooManyRequests -> Res.string.error_too_many_requests
        LoginError.SignIn.UnknownError -> Res.string.error_unknown_signin_error
        LoginError.SignUp.EmailAlreadyInUse -> Res.string.error_email_already_in_use
        LoginError.SignUp.WeakPassword -> Res.string.error_weak_password
        LoginError.SignUp.InvalidEmail -> Res.string.error_invalid_email
        LoginError.SignUp.OperationNotAllowed -> Res.string.error_operation_not_allowed
        LoginError.SignUp.NetworkError -> Res.string.error_network_error
        LoginError.SignUp.UnknownError -> Res.string.error_unknown_signup_error
    }

    return UiText.StringResourceId(stringRes)
}