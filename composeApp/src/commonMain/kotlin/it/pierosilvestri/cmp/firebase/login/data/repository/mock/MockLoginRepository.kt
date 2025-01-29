package it.pierosilvestri.cmp.firebase.login.data.repository.mock

import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.login.domain.LoginError
import it.pierosilvestri.cmp.firebase.login.domain.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockLoginRepository: LoginRepository {

    override val currentUser: Flow<User>
        get() = flow {
            emit(User())
        }

    override suspend fun login(email: String, password: String): Result<User, LoginError.SignIn> {
        delay(2000L)
        return try {
            // Firebase authentication logic here
            Result.Success(User())
        } catch (e: Exception) {
            Result.Error(LoginError.SignIn.UnknownError)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<User, LoginError.SignUp> {
        delay(2000L)
        return try {
            // Firebase authentication logic here
            Result.Success(User())
        } catch (e: Exception) {
            Result.Error(LoginError.SignUp.UnknownError)
        }
    }

    override suspend fun signOut(): Result<Unit, LoginError.SignOut> {
        TODO("Not yet implemented")
    }

    override suspend fun loginWithGoogle(): Result<User, LoginError.SignIn> {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithGoogle(): Result<User, LoginError.SignUp> {
        TODO("Not yet implemented")
    }

}