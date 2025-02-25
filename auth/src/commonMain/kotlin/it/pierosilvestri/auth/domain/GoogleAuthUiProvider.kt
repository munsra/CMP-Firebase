package it.pierosilvestri.auth.domain

import it.pierosilvestri.auth.domain.models.GoogleAccount


expect class GoogleAuthUiProvider {
    suspend fun signIn(): GoogleAccount?
}