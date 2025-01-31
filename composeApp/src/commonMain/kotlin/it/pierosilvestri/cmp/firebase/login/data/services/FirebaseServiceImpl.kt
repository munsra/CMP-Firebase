package it.pierosilvestri.cmp.firebase.login.data.services

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.login.domain.services.FirebaseService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FirebaseServiceImpl(
    val auth: FirebaseAuth = Firebase.auth,
    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
) : FirebaseService {

    override val currentUserId: String
        get() = auth.currentUser?.uid.toString()

    override val isAuthenticated: Boolean
        get() = auth.currentUser != null && auth.currentUser?.isAnonymous == false

    override val currentUser: Flow<User?> =
        auth.authStateChanged.map { it?.let { User(it.uid, it.isEmailVerified, it.email ?: "", it.displayName ?: "", it.photoURL ?: "") } }

    private suspend fun launchWithAwait(block : suspend  () -> Unit) {
        scope.async {
            block()
        }.await()
    }
    override suspend fun authenticate(email: String, password: String): FirebaseUser? {
        var firebaseUser: FirebaseUser? = null
        launchWithAwait {
            val authResult: AuthResult = auth.signInWithEmailAndPassword(email, password)
            firebaseUser = authResult.user
        }
        return firebaseUser
    }
    override suspend fun createUser(email: String, password: String) {
        launchWithAwait {
            val authResult: AuthResult = auth.createUserWithEmailAndPassword(email, password)
            if(authResult.user != null){
                sendVerificationEmail(authResult.user!!)
            }
        }
    }

    override suspend fun signOut() {

        if (auth.currentUser?.isAnonymous == true) {
            auth.currentUser?.delete()
        }

        auth.signOut()
    }

    override suspend fun sendVerificationEmail(user: FirebaseUser) {
        launchWithAwait {
            user.sendEmailVerification()
        }
    }
}