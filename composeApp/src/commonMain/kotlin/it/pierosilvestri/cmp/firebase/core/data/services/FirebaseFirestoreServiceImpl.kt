package it.pierosilvestri.cmp.firebase.core.data.services

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import it.pierosilvestri.cmp.firebase.core.data.models.firebase.FirebaseNote
import it.pierosilvestri.cmp.firebase.todo.domain.mappers.toNote
import it.pierosilvestri.cmp.firebase.todo.domain.models.Note
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.core.domain.services.FirebaseFirestoreService
import it.pierosilvestri.cmp.firebase.todo.domain.mappers.toFirebaseNote
import kotlinx.coroutines.flow.flow

class FirebaseFirestoreServiceImpl : FirebaseFirestoreService {

    private val firestore = Firebase.firestore

    override suspend fun getUsers(): List<User> {
        val firebaseFirestore = firestore
        try {
            val userResponse =
                firebaseFirestore.collection("users").get()
            return userResponse.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            throw e
        }
    }

    /**
     * Fetches all notes for the specified userId.
     *
     * @param userId The ID of the user whose notes should be fetched.
     * @return A list of notes belonging to the user.
     * @throws Exception If the Firebase request fails.
     */
    override suspend fun getNotesForUser(userId: String): List<Note> {
        return try {
            val noteResponse = firestore.collection("notes")
                .where { "userId" equalTo userId }
                .get()
            return noteResponse.documents.map {
                it.data()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun getNotesForUserFlow(userId: String) = flow {
        firestore.collection("notes")
            .where { "userId" equalTo userId }
            .orderBy("createdAt")
            .snapshots.collect { querySnapshot ->
            val notes = querySnapshot.documents.map { documentSnapshot ->
                documentSnapshot.data<FirebaseNote>().toNote()
            }
            emit(notes)
        }
    }

    /**
     * Adds a new note to the database.
     *
     */
    override suspend fun addNote(note: Note) {
        try {
            val noteResponse = firestore.collection("notes")
                .document(note.id)
                .set(note.toFirebaseNote())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override suspend fun updateNote(note: Note) {
        try {
            val noteResponse = firestore.collection("notes")
                .document(note.id)
                .set(note.toFirebaseNote())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}