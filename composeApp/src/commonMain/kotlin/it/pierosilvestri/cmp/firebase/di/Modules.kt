package it.pierosilvestri.cmp.firebase.di

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.auth
import it.pierosilvestri.cmp.firebase.login.domain.services.FirebaseService
import it.pierosilvestri.cmp.firebase.login.data.services.FirebaseServiceImpl
import it.pierosilvestri.cmp.firebase.login.domain.repository.AuthRepository
import it.pierosilvestri.cmp.firebase.login.data.repository.AuthRepositoryImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import it.pierosilvestri.cmp.firebase.login.presentation.login_screen.LoginScreenViewModel
import it.pierosilvestri.cmp.firebase.login.presentation.signup_screen.SignUpScreenViewModel
import it.pierosilvestri.cmp.firebase.home.presentation.HomeScreenViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.viewModelOf
import it.pierosilvestri.cmp.firebase.login.presentation.splash_screen.SplashScreenViewModel
import it.pierosilvestri.cmp.firebase.login.domain.use_case.LoginUserUseCase
import it.pierosilvestri.cmp.firebase.login.domain.use_case.SignUpUseCase
import it.pierosilvestri.cmp.firebase.login.domain.use_case.ValidateEmailUseCase
import it.pierosilvestri.cmp.firebase.login.domain.use_case.ValidatePasswordUseCase


val appModule = module {
    single<FirebaseService> {
        FirebaseServiceImpl(
            auth = get(),
            scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
        )
    }

    single<FirebaseAuth> {
        Firebase.auth
    }

    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()

    // Provide EmailValidationUseCase
    factory { ValidateEmailUseCase() }

    // Provide PasswordValidationUseCase
    factory { ValidatePasswordUseCase() }

    // Provide LoginUserUseCase
    factory { SignUpUseCase(get(), get(), get()) }

    // Provide LoginUserUseCase
    factory { LoginUserUseCase(get(), get()) }

    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::LoginScreenViewModel)
    viewModelOf(::SignUpScreenViewModel)
    viewModelOf(::HomeScreenViewModel)


}