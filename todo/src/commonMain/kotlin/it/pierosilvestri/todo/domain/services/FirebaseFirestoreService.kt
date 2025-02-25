package it.pierosilvestri.todo.domain.services


import it.pierosilvestri.todo.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface FirebaseFirestoreService {
    suspend fun getNotesForUser(userId: String): List<Note>
    suspend fun getNotesForUserFlow(userId: String): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
}