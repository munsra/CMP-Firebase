package it.pierosilvestri.auth.domain

import androidx.compose.runtime.Composable
import cocoapods.GoogleSignIn.GIDSignIn
import kotlinx.cinterop.ExperimentalForeignApi

actual class GoogleAuthProvider {
    @Composable
    actual fun getUiProvider(): GoogleAuthUiProvider = GoogleAuthUiProvider()


    @OptIn(ExperimentalForeignApi::class)
    actual suspend fun signOut() {
        GIDSignIn.sharedInstance.signOut()
    }
}