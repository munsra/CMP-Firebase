package it.pierosilvestri.todo.di


import it.pierosilvestri.todo.data.repository.TodoRepositoryImpl
import it.pierosilvestri.todo.data.services.FirebaseFirestoreServiceImpl
import it.pierosilvestri.todo.domain.repository.TodoRepository
import it.pierosilvestri.todo.domain.services.FirebaseFirestoreService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModelOf
import it.pierosilvestri.todo.presentation.TodoScreenViewModel

val todoModule = module {

    single<FirebaseFirestoreService> {
        FirebaseFirestoreServiceImpl()
    }

    singleOf(::TodoRepositoryImpl).bind<TodoRepository>()

    viewModelOf(::TodoScreenViewModel)
}