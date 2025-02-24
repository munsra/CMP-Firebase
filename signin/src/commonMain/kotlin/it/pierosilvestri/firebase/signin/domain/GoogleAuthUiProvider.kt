package it.pierosilvestri.firebase.signin.domain

import it.pierosilvestri.firebase.signin.domain.models.GoogleAccount

expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
}