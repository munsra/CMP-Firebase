package it.pierosilvestri.auth.domain.repository

import it.pierosilvestri.auth.domain.AuthError
import it.pierosilvestri.core.domain.models.User
import kotlinx.coroutines.flow.Flow
import it.pierosilvestri.core.domain.Result

interface AuthRepository {
    val currentUserId: String
    val currentUser: Flow<User?>
    suspend fun login(email: String, password: String): Result<User, AuthError.SignIn>
    suspend fun signUp(email: String, password: String): Result<Unit, AuthError.SignUp>
    suspend fun signOut(): Result<Unit, AuthError.SignOut>
    suspend fun loginWithGoogle(): Result<User, AuthError.SignIn>
    suspend fun signUpWithGoogle(): Result<User, AuthError.SignUp>
}