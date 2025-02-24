package it.pierosilvestri.cmp.firebase.utils

import cmp_firebase.composeapp.generated.resources.Res
import cmp_firebase.composeapp.generated.resources.google_icon
import it.pierosilvestri.core.domain.Drawables
import it.pierosilvestri.core.domain.Resources
import org.jetbrains.compose.resources.ExperimentalResourceApi

class ResourcesImpl : Resources {
    override val drawables: Drawables = DrawablesImpl()
}

@OptIn(ExperimentalResourceApi::class)
private class DrawablesImpl : Drawables {
    override val googleIcon = Res.drawable.google_icon
}