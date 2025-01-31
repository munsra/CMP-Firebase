package it.pierosilvestri.cmp.firebase.login.data.mapper

import dev.gitlive.firebase.auth.FirebaseUser
import it.pierosilvestri.cmp.firebase.core.domain.models.User

fun FirebaseUser.toUser(): User {
    return User(
        id = this.uid,
        isAuthenticated = this.isEmailVerified,
        email = this.email ?: "",
        displayName = this.displayName ?: "",
        photoUrl = this.photoURL ?: "",
    )
}