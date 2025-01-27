package it.pierosilvestri.cmp.firebase.login.data.repository

import dev.gitlive.firebase.auth.FirebaseAuth
import it.pierosilvestri.cmp.firebase.core.domain.Result
import it.pierosilvestri.cmp.firebase.login.domain.LoginError
import it.pierosilvestri.cmp.firebase.login.domain.LoginRepository
import it.pierosilvestri.cmp.firebase.login.domain.handleLoginAuthErrorToLoginError

class LoginRepositoryImpl(
    private val auth: FirebaseAuth
): LoginRepository {

    override suspend fun login(email: String, password: String): Result<Unit, LoginError.SignIn> {
        return try {
            if(email.isBlank() || password.isBlank()){
                return Result.Error(LoginError.SignIn.EmptyFields)
            }
            val result = auth.signInWithEmailAndPassword(email, password)
            return Result.Success(Unit)
        } catch (e: Exception) {
                val error: LoginError = handleLoginAuthErrorToLoginError(e)
            if(error is LoginError.SignIn){
                return Result.Error(error)
            }
            return Result.Error(LoginError.SignIn.UnknownError)
        }
    }

    override suspend fun signUp(email: String, password: String): Result<Unit, LoginError.SignUp> {
        TODO("Not yet implemented")
    }

}