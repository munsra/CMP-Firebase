package it.pierosilvestri.core.domain.compositionlocals

import androidx.compose.runtime.compositionLocalOf
import it.pierosilvestri.core.domain.Resources

val LocalResources = compositionLocalOf<Resources> {
    error("No LocalResources provided")
}