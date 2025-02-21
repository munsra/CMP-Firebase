package it.pierosilvestri.cmp.firebase.core.domain.services

import dev.gitlive.firebase.auth.FirebaseUser
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthService {

    val currentUserId: String
    val isAuthenticated: Boolean

    val currentUser: Flow<User?>

    suspend fun authenticate(email: String, password: String): FirebaseUser?
    suspend fun createUser(email: String, password: String)

    suspend fun signOut()
    suspend fun sendVerificationEmail(user: FirebaseUser)
}