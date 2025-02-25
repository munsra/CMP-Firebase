package it.pierosilvestri.login.domain.models

data class Profile(
    val name: String,
    val familyName: String,
    val givenName: String,
    val email: String,
    val picture: String?
)