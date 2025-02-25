package it.pierosilvestri.auth.domain

import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

actual fun handleFirebaseErrorToAuthError(e: Exception): AuthError {
    return when (e) {
        is FirebaseAuthInvalidCredentialsException -> {
            return when (e.errorCode) {
                "ERROR_INVALID_EMAIL" -> AuthError.SignIn.InvalidEmail
                "ERROR_USER_NOT_FOUND" -> AuthError.SignIn.UserNotFound
                "ERROR_WRONG_PASSWORD" -> AuthError.SignIn.WrongPassword
                "ERROR_INVALID_CREDENTIAL" -> AuthError.SignIn.InvalidCredentials
                else -> AuthError.SignIn.UnknownError
            }
        }
        else -> AuthError.SignIn.UnknownError
    }
}