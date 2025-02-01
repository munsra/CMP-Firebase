package it.pierosilvestri.cmp.firebase.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object SplashScreen: Route

    @Serializable
    data object LoginScreen: Route

    @Serializable
    data object SignUpScreen: Route

    @Serializable
    data object HomeScreen: Route

    @Serializable
    data object TodoScreen: Route


}