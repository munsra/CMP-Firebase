package it.pierosilvestri.cmp.firebase.login.domain

import it.pierosilvestri.cmp.firebase.core.domain.DataError
import it.pierosilvestri.cmp.firebase.core.domain.Result

interface LoginRepository {
    suspend fun login(email: String, password: String): Result<Unit, LoginError.SignIn>
    suspend fun signUp(email: String, password: String): Result<Unit, LoginError.SignUp>

}