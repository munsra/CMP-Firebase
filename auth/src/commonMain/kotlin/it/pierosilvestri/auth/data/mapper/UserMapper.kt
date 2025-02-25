package it.pierosilvestri.auth.data.mapper

import dev.gitlive.firebase.auth.FirebaseUser
import it.pierosilvestri.core.domain.models.User

fun FirebaseUser.toUser(): User {
    return User(
        id = this.uid,
        isAuthenticated = this.isEmailVerified,
        email = this.email ?: "",
        displayName = this.displayName ?: "",
        photoUrl = this.photoURL ?: "",
    )
}