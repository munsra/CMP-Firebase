package it.pierosilvestri.firebase.signin.domain.models

data class GoogleAccount(
    val token: String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)