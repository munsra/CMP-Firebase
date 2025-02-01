package it.pierosilvestri.cmp.firebase.core.domain.services

import it.pierosilvestri.cmp.firebase.core.domain.models.Note
import it.pierosilvestri.cmp.firebase.core.domain.models.User

interface FirebaseFirestoreService {
    suspend fun getUsers(): List<User>
    suspend fun getNotesForUser(userId: String): List<Note>
    suspend fun addNote(note: Note)
}