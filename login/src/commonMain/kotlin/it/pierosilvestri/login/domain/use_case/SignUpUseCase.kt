package it.pierosilvestri.login.domain.use_case

import it.pierosilvestri.auth.domain.repository.AuthRepository
import it.pierosilvestri.core.domain.Result
import it.pierosilvestri.core.domain.Error

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
) {
    suspend operator fun invoke(email: String?, password: String?): Result<Unit, Error> {
        val emailValidation = validateEmailUseCase(email)
        if (emailValidation is Result.Error) {
            return emailValidation
        }

        val passwordValidation = validatePasswordUseCase(password)
        if(passwordValidation is Result.Error) {
            return passwordValidation
        }

        return authRepository.signUp(email!!, password!!)
    }
}