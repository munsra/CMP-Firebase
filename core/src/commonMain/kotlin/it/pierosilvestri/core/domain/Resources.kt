package it.pierosilvestri.core.domain

import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

interface Resources {
    val drawables: Drawables
}

@OptIn(ExperimentalResourceApi::class)
interface Drawables {
    val googleIcon: DrawableResource
}