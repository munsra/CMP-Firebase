package it.pierosilvestri.cmp.firebase.login.domain

import dev.gitlive.firebase.FirebaseException
import it.pierosilvestri.cmp.firebase.core.domain.Error

sealed interface LoginError: Error {
    enum class SignIn : LoginError {
        InvalidEmail,
        UserNotFound,
        WrongPassword,
        UserDisabled,
        NetworkError,
        TooManyRequests,
        UnknownError,
        EmptyFields,
        InvalidCredentials
    }

    enum class SignUp : LoginError {
        EmailAlreadyInUse,
        WeakPassword,
        InvalidEmail,
        OperationNotAllowed,
        NetworkError,
        UnknownError,
        EmptyFields,
        InvalidCredentials
    }
}

expect fun handleLoginAuthErrorToLoginError(e: Exception): LoginError.SignIn
