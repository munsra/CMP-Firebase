package it.pierosilvestri.cmp.firebase.login.domain

import dev.gitlive.firebase.FirebaseNetworkException
import dev.gitlive.firebase.FirebaseTooManyRequestsException
import dev.gitlive.firebase.auth.FirebaseAuthEmailException
import dev.gitlive.firebase.auth.FirebaseAuthInvalidCredentialsException
import dev.gitlive.firebase.auth.FirebaseAuthUserCollisionException
import dev.gitlive.firebase.auth.FirebaseAuthWeakPasswordException
import it.pierosilvestri.core.domain.Error

sealed interface AuthError: Error {

    object UnknownError: AuthError

    enum class SignIn : AuthError {
        InvalidEmail,
        UserNotFound,
        WrongPassword,
        UserDisabled,
        NetworkError,
        TooManyRequests,
        UnknownError,
        EmptyFields,
        InvalidCredentials,
        NotVerified,
        NoGoogleAccoundFound
    }

    enum class SignUp : AuthError {
        EmailAlreadyInUse,
        WeakPassword,
        InvalidEmail,
        OperationNotAllowed,
        NetworkError,
        UnknownError,
        EmptyFields,
        InvalidCredentials
    }

    enum class SignOut: AuthError {
        UnknownError
    }
}

fun handleSignInErrorToAuthError(e: Exception): AuthError.SignIn {
    return when (e) {
        is FirebaseAuthInvalidCredentialsException -> {
            val firebaseAuthError: AuthError = handleFirebaseErrorToAuthError(e)
            if(firebaseAuthError is AuthError.SignIn) {
                return firebaseAuthError
            }
            return AuthError.SignIn.UnknownError
        }
        is FirebaseAuthEmailException -> AuthError.SignIn.InvalidEmail
        is FirebaseAuthWeakPasswordException -> AuthError.SignIn.WrongPassword
        is FirebaseNetworkException -> AuthError.SignIn.NetworkError
        is FirebaseTooManyRequestsException -> AuthError.SignIn.TooManyRequests
        else -> AuthError.SignIn.UnknownError
    }
}

expect fun handleFirebaseErrorToAuthError(e: Exception): AuthError

fun handleSignUpErrorToAuthError(e: Exception): AuthError.SignUp {
    return when (e) {
        is FirebaseAuthUserCollisionException ->  AuthError.SignUp.EmailAlreadyInUse
        else -> AuthError.SignUp.UnknownError
    }
}
