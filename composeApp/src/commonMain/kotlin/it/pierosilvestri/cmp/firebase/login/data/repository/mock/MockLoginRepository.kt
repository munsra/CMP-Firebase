package it.pierosilvestri.cmp.firebase.login.data.repository.mock

import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.login.domain.LoginError
import it.pierosilvestri.cmp.firebase.login.domain.LoginRepository
import kotlinx.coroutines.delay

class MockLoginRepository: LoginRepository {

    override suspend fun login(email: String, password: String): Result<Unit, LoginError.SignIn> {
        delay(2000L)
        return try {
            // Firebase authentication logic here
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(LoginError.SignIn.UnknownError)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit, LoginError.SignUp> {
        delay(2000L)
        return try {
            // Firebase authentication logic here
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(LoginError.SignUp.UnknownError)
        }
    }

}