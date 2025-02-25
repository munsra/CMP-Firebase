package it.pierosilvestri.todo.data.models.firebase

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseNote(
    val uid: String,
    val title: String?,
    val content: String,
    val createdAt: Timestamp,
    val isCompleted: Boolean,
    val userId: String
)