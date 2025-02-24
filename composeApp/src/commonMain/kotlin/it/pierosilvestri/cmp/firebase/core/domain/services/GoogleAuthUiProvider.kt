package it.pierosilvestri.cmp.firebase.core.domain.services

import it.pierosilvestri.cmp.firebase.core.domain.models.GoogleAccount

expect class GoogleAuthUiProvider {
   suspend fun signIn(): GoogleAccount?
}