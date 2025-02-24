package it.pierosilvestri.cmp.firebase.core.domain.services

import androidx.compose.runtime.Composable

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider {
        TODO("Not yet implemented")
    }

    actual suspend fun signOut() {
    }

}