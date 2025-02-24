package it.pierosilvestri.cmp.firebase.core.domain.services

import androidx.compose.runtime.Composable

expect class GoogleAuthProvider {
    @Composable
    fun getUiProvider(): GoogleAuthUiProvider

    suspend fun signOut()
}
