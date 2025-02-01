package it.pierosilvestri.cmp.firebase.core.data.services

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import it.pierosilvestri.cmp.firebase.core.domain.models.Note
import it.pierosilvestri.cmp.firebase.core.domain.models.User
import it.pierosilvestri.cmp.firebase.core.domain.services.FirebaseFirestoreService

class FirebaseFirestoreServiceImpl: FirebaseFirestoreService {
    override suspend fun getUsers(): List<User> {
        val firebaseFirestore = Firebase.firestore
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
            val noteResponse = Firebase.firestore.collection("notes")
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

    override suspend fun addNote(note: Note) {
        try {
            val noteResponse = Firebase.firestore.collection("notes")
                .add(note)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}