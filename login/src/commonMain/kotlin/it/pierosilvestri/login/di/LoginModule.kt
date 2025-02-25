package it.pierosilvestri.login.di

import it.pierosilvestri.login.domain.use_case.LoginUserUseCase
import it.pierosilvestri.login.domain.use_case.SignUpUseCase
import it.pierosilvestri.login.domain.use_case.ValidateEmailUseCase
import it.pierosilvestri.login.domain.use_case.ValidatePasswordUseCase
import it.pierosilvestri.login.presentation.login_screen.LoginScreenViewModel
import it.pierosilvestri.login.presentation.signup_screen.SignUpScreenViewModel
import it.pierosilvestri.login.presentation.splash_screen.SplashScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
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
}