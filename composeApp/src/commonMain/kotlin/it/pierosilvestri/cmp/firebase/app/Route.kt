package it.pierosilvestri.cmp.firebase.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object Graph: Route

    @Serializable
    data object LoginScreen: Route

    @Serializable
    data object HomeScreen: Route

}