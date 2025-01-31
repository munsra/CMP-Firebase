package it.pierosilvestri.cmp.firebase.login.domain.use_case

import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.login.domain.AuthError
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.core.domain.Error

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