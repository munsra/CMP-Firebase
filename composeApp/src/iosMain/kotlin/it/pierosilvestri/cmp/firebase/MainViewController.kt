package it.pierosilvestri.cmp.firebase

import androidx.compose.ui.window.ComposeUIViewController
import it.pierosilvestri.cmp.firebase.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}