package it.pierosilvestri.cmp.firebase.core.domain.services

import it.pierosilvestri.cmp.firebase.core.domain.models.GoogleAccount

actual class GoogleAuthUiProvider {
    actual suspend fun signIn(): GoogleAccount? {
        TODO("Not yet implemented")
    }
}