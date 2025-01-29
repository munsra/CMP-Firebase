package it.pierosilvestri.cmp.firebase.login.data.repository

import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.login.domain.AuthService
import it.pierosilvestri.cmp.firebase.login.domain.LoginError
import it.pierosilvestri.cmp.firebase.login.domain.LoginRepository
import it.pierosilvestri.cmp.firebase.login.domain.handleLoginAuthErrorToLoginError
import kotlinx.coroutines.flow.Flow

class LoginRepositoryImpl(
    private val auth: AuthService
) : LoginRepository {

    override val currentUser: Flow<User>
        get() = auth.currentUser

    override suspend fun login(email: String, password: String): Result<User, LoginError.SignIn> {
        try {
            if (email.isBlank() || password.isBlank()) {
                return Result.Error(LoginError.SignIn.EmptyFields)
            }
            auth.authenticate(email, password)
            return Result.Success(User())
        } catch (e: Exception) {
            val error: LoginError = handleLoginAuthErrorToLoginError(e)
            if (error is LoginError.SignIn) {
                return Result.Error(error)
            }
            return Result.Error(LoginError.SignIn.UnknownError)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<User, LoginError.SignUp> {
        TODO("Not yet implemented")
    }

    override suspend fun signOut(): Result<Unit, LoginError.SignOut>  {
        try {
            auth.signOut()
            return Result.Success(Unit)
        } catch (e: Exception) {
            return Result.Error(LoginError.SignOut.UnknownError)
        }
    }

    override suspend fun loginWithGoogle(): Result<User, LoginError.SignIn> {
        TODO("Not yet implemented")
    }

    override suspend fun signUpWithGoogle(): Result<User, LoginError.SignUp> {
        TODO("Not yet implemented")
    }

}