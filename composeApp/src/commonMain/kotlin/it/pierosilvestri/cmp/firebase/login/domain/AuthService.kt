package it.pierosilvestri.cmp.firebase.login.domain

import it.pierosilvestri.cmp.firebase.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface AuthService {

    val currentUserId: String
    val isAuthenticated: Boolean

    val currentUser: Flow<User?>

    suspend fun authenticate(email: String, password: String)
    suspend fun createUser(email: String, password: String)

    suspend fun signOut()
}