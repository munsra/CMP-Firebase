package it.pierosilvestri.cmp.firebase.login.domain

import it.pierosilvestri.cmp.firebase.core.domain.DataError
import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    val currentUser: Flow<User?>
    suspend fun login(email: String, password: String): Result<User, LoginError.SignIn>
    suspend fun signUp(email: String, password: String): Result<User, LoginError.SignUp>
    suspend fun signOut(): Result<Unit, LoginError.SignOut>
    suspend fun loginWithGoogle(): Result<User, LoginError.SignIn>
    suspend fun signUpWithGoogle(): Result<User, LoginError.SignUp>
}