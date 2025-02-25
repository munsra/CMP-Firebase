package it.pierosilvestri.auth.data.repository.mock

import it.pierosilvestri.auth.domain.AuthError
import it.pierosilvestri.auth.domain.repository.AuthRepository
import it.pierosilvestri.core.domain.Result
import it.pierosilvestri.core.domain.models.User
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockAuthRepository: AuthRepository {
    override val currentUserId: String
        get() = "1"

    override val currentUser: Flow<User>
        get() = flow {
            emit(
                User(
                id = "1",
                isAuthenticated = true,
                email = "james.a.garfield@examplepetstore.com",
                displayName = "John Doe",
                photoUrl = null
            )
            )
        }

    override suspend fun login(email: String, password: String): Result<User, AuthError.SignIn> {
        delay(2000L)
        return try {
            // Firebase authentication logic here
            Result.Success(
                User(
                id = "1",
                isAuthenticated = true,
                email = "william.henry.harrison@example-pet-store.com",
                displayName = "John Doe",
                photoUrl = null
            )
            )
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
        return Result.Success(Unit)
    }

    override suspend fun loginWithGoogle(): Result<User, AuthError.SignIn> {
        return Result.Success(
            User(
            id = "1",
            isAuthenticated = true,
            email = "william.henry.harrison@example-pet-store.com",
            displayName = "John Doe",
            photoUrl = null
        )
        )
    }

    override suspend fun signUpWithGoogle(): Result<User, AuthError.SignUp> {
        return Result.Success(
            User(
            id = "1",
            isAuthenticated = true,
            email = "william.henry.harrison@example-pet-store.com",
            displayName = "John Doe",
            photoUrl = null
        )
        )
    }

}