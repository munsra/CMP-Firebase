package it.pierosilvestri.cmp.firebase.login.data.repository.mock

import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.login.domain.AuthError
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockAuthRepository: AuthRepository {

    override val currentUser: Flow<User>
        get() = flow {
            emit(User())
        }

    override suspend fun login(email: String, password: String): Result<User, AuthError.SignIn> {
        delay(2000L)
        return try {
            // Firebase authentication logic here
            Result.Success(User())
        } catch (e: Exception) {
            Result.Error(AuthError.SignIn.UnknownError)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit, AuthError.SignUp> {
        delay(2000L)
        return try {
            // Firebase authentication logic here
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(AuthError.SignUp.UnknownError)
        }
    }

    override suspend fun signOut(): Result<Unit, AuthError.SignOut> {
        TODO("Not yet implemented")
    }

    override suspend fun loginWithGoogle(): Result<User, AuthError.SignIn> {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithGoogle(): Result<User, AuthError.SignUp> {
        TODO("Not yet implemented")
    }

}