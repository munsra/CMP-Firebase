package it.pierosilvestri.cmp.firebase.core.domain.models

data class User(
    val id: String,
    val isAuthenticated: Boolean,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
)