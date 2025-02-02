package it.pierosilvestri.cmp.firebase.login.domain.repository

import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.login.domain.AuthError
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUserId: String
    val currentUser: Flow<User?>
    suspend fun login(email: String, password: String): Result<User, AuthError.SignIn>
    suspend fun signUp(email: String, password: String): Result<Unit, AuthError.SignUp>
    suspend fun signOut(): Result<Unit, AuthError.SignOut>
    suspend fun loginWithGoogle(): Result<User, AuthError.SignIn>
    suspend fun signUpWithGoogle(): Result<User, AuthError.SignUp>
}