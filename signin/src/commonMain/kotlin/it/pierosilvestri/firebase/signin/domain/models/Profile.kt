package it.pierosilvestri.firebase.signin.domain.models

data class Profile(
    val name: String,
    val familyName: String,
    val givenName: String,
    val email: String,
    val picture: String?
)