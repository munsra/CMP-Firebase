package it.pierosilvestri.todo.data.repository

import it.pierosilvestri.todo.domain.models.Note
import it.pierosilvestri.todo.domain.services.FirebaseFirestoreService
import it.pierosilvestri.todo.domain.repository.TodoRepository
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

    override suspend fun updateNote(note: Note) {
        firebaseFirestoreService.updateNote(note)
    }

}