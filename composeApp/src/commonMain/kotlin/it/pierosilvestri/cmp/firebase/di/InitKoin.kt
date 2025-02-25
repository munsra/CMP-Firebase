package it.pierosilvestri.cmp.firebase.di

import it.pierosilvestri.auth.di.authModule
import it.pierosilvestri.auth.di.loinPlatformModule
import it.pierosilvestri.home.di.homeModule
import it.pierosilvestri.login.di.loginModule
import it.pierosilvestri.todo.di.todoModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(authModule, loginModule, homeModule, todoModule, loinPlatformModule)
    }
}