package it.pierosilvestri.home.di

import it.pierosilvestri.home.presentation.HomeScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeModule = module {
    viewModelOf(::HomeScreenViewModel)
}