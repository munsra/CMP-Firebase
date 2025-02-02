package it.pierosilvestri.cmp.firebase.todo.data.repository

import it.pierosilvestri.cmp.firebase.core.domain.models.Note
import it.pierosilvestri.cmp.firebase.core.domain.services.FirebaseFirestoreService
import it.pierosilvestri.cmp.firebase.todo.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow

class TodoRepositoryImpl(
    private val firebaseFirestoreService: FirebaseFirestoreService
): TodoRepository {

    override suspend fun getNotesForUser(userId: String): Flow<List<Note>> {
        return firebaseFirestoreService.getNotesForUserFlow(userId)
    }

    override suspend fun addNote(note: Note) {
        firebaseFirestoreService.addNote(note)
    }

}