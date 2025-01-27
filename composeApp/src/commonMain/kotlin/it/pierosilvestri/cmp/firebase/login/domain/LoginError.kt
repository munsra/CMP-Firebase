package it.pierosilvestri.cmp.firebase.login.domain

import it.pierosilvestri.cmp.firebase.core.domain.Error

sealed interface LoginError: Error {
    enum class SignIn : LoginError {
        InvalidEmail,
        UserNotFound,
        WrongPassword,
        UserDisabled,
        NetworkError,
        TooManyRequests,
        UnknownError
    }

    enum class SignUp : LoginError {
        EmailAlreadyInUse,
        WeakPassword,
        InvalidEmail,
        OperationNotAllowed,
        NetworkError,
        UnknownError
    }
}