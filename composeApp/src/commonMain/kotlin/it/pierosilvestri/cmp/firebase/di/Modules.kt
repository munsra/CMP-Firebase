package it.pierosilvestri.bookpedia.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import it.pierosilvestri.cmp.firebase.login.domain.AuthService
import it.pierosilvestri.cmp.firebase.login.data.services.AuthServiceImpl
import it.pierosilvestri.cmp.firebase.login.domain.LoginRepository
import it.pierosilvestri.cmp.firebase.login.data.repository.LoginRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenViewModel
import it.pierosilvestri.cmp.firebase.home.presentation.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.viewModelOf
import it.pierosilvestri.cmp.firebase.login.presentation.splash_screen.SplashScreenViewModel


val appModule = module {
    single<AuthService> {
        AuthServiceImpl(
            auth = get(),
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        )
    }

    single<FirebaseAuth> {
        Firebase.auth
    }

    singleOf(::LoginRepositoryImpl).bind<LoginRepository>()

    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::HomeScreenViewModel)
}