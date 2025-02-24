package it.pierosilvestri.cmp.firebase.login.domain.use_case

import it.pierosilvestri.core.domain.Result
import it.pierosilvestri.cmp.firebase.login.domain.ValidationError

class ValidatePasswordUseCase {
    operator fun invoke(password: String?): Result<Unit, ValidationError.Password> {
        if (password.isNullOrBlank()) {
            return Result.Error(ValidationError.Password.IsEmpty)
        }

        if (password.length < MIN_PASSWORD_LENGTH) {
            return Result.Error(ValidationError.Password.IsTooShort)
        }

        if (!password.containsUpperCase()) {
            return Result.Error(ValidationError.Password.HasNotAnyUpperCase)
        }

        if (!password.containsDigit()) {
            return Result.Error(ValidationError.Password.HasNoNumber)
        }

        if (!password.containsSpecialCharacter()) {
            return Result.Error(ValidationError.Password.HasNotSpecialCharacter)
        }

        return Result.Success(Unit)
    }

    private fun String.containsUpperCase(): Boolean = any { it.isUpperCase() }

    private fun String.containsDigit(): Boolean = any { it.isDigit() }

    private fun String.containsSpecialCharacter(): Boolean {
        val specialCharRegex = "[!@#\$%^&*(),.?\":{}|<>]".toRegex()
        return specialCharRegex.containsMatchIn(this)
    }

    companion object {
        private const val MIN_PASSWORD_LENGTH = 8
    }
}