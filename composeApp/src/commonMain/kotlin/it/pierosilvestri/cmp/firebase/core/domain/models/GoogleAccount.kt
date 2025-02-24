package it.pierosilvestri.cmp.firebase.core.domain.models

data class GoogleAccount(
    val token: String,
    val displayName: String = "",
    val profileImageUrl: String? = null
)