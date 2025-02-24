package it.pierosilvestri.cmp.firebase


import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import it.pierosilvestri.cmp.firebase.app.Navigation
import it.pierosilvestri.cmp.firebase.utils.ResourcesImpl
import it.pierosilvestri.core_ui.presentation.theme.AppTheme
import it.pierosilvestri.core.domain.compositionlocals.LocalResources

@Composable
@Preview
fun App() {

    CompositionLocalProvider(LocalResources provides ResourcesImpl()) {
        AppTheme(
            dynamicColor = false
        ) {
            Navigation()
        }
    }
}