package it.pierosilvestri.todo.data.services

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import it.pierosilvestri.todo.domain.models.Note
import it.pierosilvestri.todo.data.models.firebase.FirebaseNote
import it.pierosilvestri.todo.domain.services.FirebaseFirestoreService
import kotlinx.coroutines.flow.flow
import it.pierosilvestri.todo.domain.mappers.toNote
import it.pierosilvestri.todo.domain.mappers.toFirebaseNote

class FirebaseFirestoreServiceImpl : FirebaseFirestoreService {

    private val firestore = Firebase.firestore

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