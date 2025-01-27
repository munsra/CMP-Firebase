package it.pierosilvestri.cmp.firebase.login.domain

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthEmailException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

actual fun handleLoginAuthErrorToLoginError(e: Exception): LoginError.SignIn {
    return when (e) {
        is FirebaseAuthInvalidCredentialsException -> {
            return when (e.errorCode) {
                "ERROR_INVALID_EMAIL" -> LoginError.SignIn.InvalidEmail
                "ERROR_USER_NOT_FOUND" -> LoginError.SignIn.UserNotFound
                "ERROR_WRONG_PASSWORD" -> LoginError.SignIn.WrongPassword
                "ERROR_INVALID_CREDENTIAL" -> LoginError.SignIn.InvalidCredentials
                else -> LoginError.SignIn.UnknownError
            }
        }
        is FirebaseAuthEmailException -> LoginError.SignIn.InvalidEmail
        is FirebaseAuthWeakPasswordException -> LoginError.SignIn.WrongPassword
        is FirebaseNetworkException -> LoginError.SignIn.NetworkError
        is FirebaseTooManyRequestsException -> LoginError.SignIn.TooManyRequests
        else -> LoginError.SignIn.UnknownError
    }
}
