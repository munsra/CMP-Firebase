package it.pierosilvestri.cmp.firebase.todo.domain.repository

import it.pierosilvestri.cmp.firebase.todo.domain.models.Note
import kotlinx.coroutines.flow.Flow

interface TodoRepository {
    suspend fun getNotesForUser(userId: String): Flow<List<Note>>
    suspend fun addNote(note: Note)
    suspend fun updateNote(note: Note)
}