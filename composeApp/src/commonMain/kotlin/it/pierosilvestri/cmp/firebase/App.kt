package it.pierosilvestri.cmp.firebase


import androidx.compose.runtime.*
import org.jetbrains.compose.ui.tooling.preview.Preview

import it.pierosilvestri.cmp.firebase.app.Navigation
import it.pierosilvestri.cmp.firebase.core_ui.theme.AppTheme

@Composable
@Preview
fun App() {
    AppTheme(
        dynamicColor = false
    ) {
        Navigation()
    }
}