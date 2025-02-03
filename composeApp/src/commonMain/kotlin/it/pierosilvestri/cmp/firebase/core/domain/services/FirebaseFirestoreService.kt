package it.pierosilvestri.cmp.firebase.core.domain.services

import it.pierosilvestri.cmp.firebase.todo.domain.models.Note
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import kotlinx.coroutines.flow.Flow

interface FirebaseFirestoreService {
    suspend fun getUsers(): List<User>
    suspend fun getNotesForUser(userId: String): List<Note>
    suspend fun getNotesForUserFlow(userId: String): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
}