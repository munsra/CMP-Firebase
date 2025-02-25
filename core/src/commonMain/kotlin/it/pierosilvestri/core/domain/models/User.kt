package it.pierosilvestri.core.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String,
    val isAuthenticated: Boolean,
    val email: String,
    val displayName: String,
    val photoUrl: String?,
)