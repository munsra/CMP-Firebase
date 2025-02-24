package it.pierosilvestri.cmp.firebase.di

import it.pierosilvestri.firebase.signin.di.loinPlatformModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(appModule, loinPlatformModule)
    }
}