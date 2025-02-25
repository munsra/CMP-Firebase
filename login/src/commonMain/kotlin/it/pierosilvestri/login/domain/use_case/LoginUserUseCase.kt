package it.pierosilvestri.login.domain.use_case

import it.pierosilvestri.auth.domain.AuthError
import it.pierosilvestri.auth.domain.repository.AuthRepository
import it.pierosilvestri.core.domain.models.User
import it.pierosilvestri.core.domain.Result
import it.pierosilvestri.core.domain.Error

class LoginUserUseCase(
    private val authRepository: AuthRepository,
    private val validateEmailUseCase: ValidateEmailUseCase
) {

    suspend operator fun invoke(email: String?, password: String?): Result<User, Error> {
        val emailValidation = validateEmailUseCase(email)
        if(emailValidation is Result.Error) {
            return emailValidation
        }

        if (password.isNullOrBlank()) {
            return Result.Error(AuthError.SignIn.EmptyFields)
        }

        return authRepository.login(email!!, password)
    }

}