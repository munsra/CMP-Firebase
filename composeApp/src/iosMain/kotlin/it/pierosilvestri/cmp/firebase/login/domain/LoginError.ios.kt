package it.pierosilvestri.cmp.firebase.login.domain

import dev.gitlive.firebase.FirebaseNetworkException
import dev.gitlive.firebase.FirebaseTooManyRequestsException
import dev.gitlive.firebase.auth.FirebaseAuthEmailException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException


actual fun handleLoginAuthErrorToLoginError(e: Exception): LoginError.SignIn {
    return when (e) {
        is FirebaseAuthInvalidCredentialsException -> LoginError.SignIn.UserNotFound
        is FirebaseAuthEmailException -> LoginError.SignIn.InvalidEmail
        is FirebaseAuthWeakPasswordException -> LoginError.SignIn.WrongPassword
        is FirebaseNetworkException -> LoginError.SignIn.NetworkError
        is FirebaseTooManyRequestsException -> LoginError.SignIn.TooManyRequests
        else -> LoginError.SignIn.UnknownError
    }
}
