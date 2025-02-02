package it.pierosilvestri.cmp.firebase.login.data.repository

import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.login.data.mapper.toUser
import it.pierosilvestri.cmp.firebase.core.domain.services.FirebaseAuthService
import it.pierosilvestri.cmp.firebase.login.domain.AuthError
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import it.pierosilvestri.cmp.firebase.login.domain.handleSignInErrorToAuthError
import it.pierosilvestri.cmp.firebase.login.domain.handleSignUpErrorToAuthError
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val auth: FirebaseAuthService
) : AuthRepository {

    override val currentUserId: String
        get() = auth.currentUserId

    override val currentUser: Flow<User?>
        get() = auth.currentUser

    override suspend fun login(email: String, password: String): Result<User, AuthError.SignIn> {
        try {
            if (email.isBlank() || password.isBlank()) {
                return Result.Error(AuthError.SignIn.EmptyFields)
            }
            val user = auth.authenticate(email, password)
            if(user == null){
                return Result.Error(AuthError.SignIn.UnknownError)
            }

            if(!user.isEmailVerified){
                return Result.Error(AuthError.SignIn.NotVerified)
            }
            return Result.Success(user.toUser())
        } catch (e: Exception) {
            return Result.Error(handleSignInErrorToAuthError(e))
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit, AuthError.SignUp> {
        try {
            auth.createUser(email, password)
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(handleSignUpErrorToAuthError(e))
        }
    }

    override suspend fun signOut(): Result<Unit, AuthError.SignOut>  {
        try {
            auth.signOut()
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(AuthError.SignOut.UnknownError)
        }
    }

    override suspend fun loginWithGoogle(): Result<User, AuthError.SignIn> {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithGoogle(): Result<User, AuthError.SignUp> {
        TODO("Not yet implemented")
    }

}