package it.pierosilvestri.cmp.firebase.login.domain

import it.pierosilvestri.cmp.firebase.core.domain.Error

sealed interface ValidationError: Error {
    enum class Email : ValidationError {
        IsEmpty,
        IsNotValid,
        UnknownError
    }

    enum class Password : ValidationError {
        IsEmpty,
        IsTooShort,
        HasNotAnyUpperCase,
        HasNoNumber,
        HasNotSpecialCharacter,
        IsWeak,
        UnknownError
    }
}