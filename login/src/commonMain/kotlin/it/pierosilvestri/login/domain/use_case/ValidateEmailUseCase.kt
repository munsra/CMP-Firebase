package it.pierosilvestri.login.domain.use_case

import it.pierosilvestri.auth.domain.ValidationError
import it.pierosilvestri.core.domain.Result

class ValidateEmailUseCase {
    // Define a regex for a valid email pattern
    private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()

    // The invoke function allows us to call the UseCase object like a function
    operator fun invoke(email: String?): Result<Unit, ValidationError.Email> {
        return if(email.isNullOrBlank()){
            Result.Error(ValidationError.Email.IsEmpty)
        }else if (email.matches(emailRegex)) {
            Result.Success(Unit)
        } else {
            Result.Error(ValidationError.Email.IsNotValid)
        }
    }
}