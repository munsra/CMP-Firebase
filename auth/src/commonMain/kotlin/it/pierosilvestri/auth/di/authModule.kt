package it.pierosilvestri.auth.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import it.pierosilvestri.auth.data.repository.AuthRepositoryImpl
import it.pierosilvestri.auth.data.services.FirebaseAuthServiceImpl
import it.pierosilvestri.auth.domain.repository.AuthRepository
import it.pierosilvestri.auth.domain.services.FirebaseAuthService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authModule = module {
    single<FirebaseAuthService> {
        FirebaseAuthServiceImpl(
            auth = get(),
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        )
    }

    single<FirebaseAuth> {
        Firebase.auth
    }

    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}