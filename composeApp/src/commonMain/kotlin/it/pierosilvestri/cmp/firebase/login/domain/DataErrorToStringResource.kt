package it.pierosilvestri.cmp.firebase.login.domain

import cmp_firebase.composeapp.generated.resources.Res
import cmp_firebase.composeapp.generated.resources.*
import it.pierosilvestri.cmp.firebase.core.presentation.UiText


fun AuthError.toUiText(): UiText {
    val stringRes = when(this) {
        AuthError.SignIn.InvalidEmail -> Res.string.error_invalid_email
        AuthError.SignIn.UserNotFound -> Res.string.error_user_not_found
        AuthError.SignIn.WrongPassword -> Res.string.error_wrong_password
        AuthError.SignIn.UserDisabled -> Res.string.error_user_disabled
        AuthError.SignIn.NetworkError -> Res.string.error_network_error
        AuthError.SignIn.TooManyRequests -> Res.string.error_too_many_requests
        AuthError.SignIn.UnknownError -> Res.string.error_unknown_signin_error
        AuthError.SignIn.EmptyFields -> Res.string.error_empty_fields
        AuthError.SignIn.InvalidCredentials -> Res.string.error_invalid_credentials
        AuthError.SignIn.NotVerified -> Res.string.error_user_not_verified

        AuthError.SignUp.EmailAlreadyInUse -> Res.string.error_email_already_in_use
        AuthError.SignUp.WeakPassword -> Res.string.error_weak_password
        AuthError.SignUp.InvalidEmail -> Res.string.error_invalid_email
        AuthError.SignUp.OperationNotAllowed -> Res.string.error_operation_not_allowed
        AuthError.SignUp.NetworkError -> Res.string.error_network_error
        AuthError.SignUp.UnknownError -> Res.string.error_unknown_signup_error
        AuthError.SignUp.EmptyFields -> Res.string.error_empty_fields
        AuthError.SignUp.InvalidCredentials -> Res.string.error_invalid_credentials

        AuthError.SignOut.UnknownError -> Res.string.logout_error_dialog_title

        AuthError.UnknownError -> Res.string.error_unknown_error
    }

    return UiText.StringResourceId(stringRes)
}

fun ValidationError.toUiText(): UiText {
    val stringRes = when(this) {
        ValidationError.Email.IsEmpty -> Res.string.error_email_empty
        ValidationError.Email.IsNotValid -> Res.string.error_invalid_email
        ValidationError.Email.UnknownError -> Res.string.error_unknown_error

        ValidationError.Password.IsEmpty -> Res.string.error_password_is_empty
        ValidationError.Password.IsTooShort -> Res.string.error_password_is_too_short
        ValidationError.Password.IsWeak -> Res.string.error_weak_password
        ValidationError.Password.HasNotAnyUpperCase -> Res.string.error_passowrd_does_not_contain_uppercase
        ValidationError.Password.HasNoNumber -> Res.string.error_passowrd_does_not_contain_number
        ValidationError.Password.HasNotSpecialCharacter -> Res.string.error_passowrd_does_not_contain_special_character
        ValidationError.Password.UnknownError -> Res.string.error_unknown_error
    }
    return UiText.StringResourceId(stringRes)
}