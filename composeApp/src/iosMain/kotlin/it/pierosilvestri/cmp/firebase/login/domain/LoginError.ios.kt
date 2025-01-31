package it.pierosilvestri.cmp.firebase.login.domain

import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException


actual fun handleFirebaseErrorToAuthError(e: Exception): AuthError {
    return when (e) {
        is FirebaseAuthInvalidCredentialsException -> AuthError.SignIn.UserNotFound
        else -> AuthError.UnknownError
    }
}
