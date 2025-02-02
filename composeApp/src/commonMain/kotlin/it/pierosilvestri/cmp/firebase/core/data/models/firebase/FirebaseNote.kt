package it.pierosilvestri.cmp.firebase.core.data.models.firebase

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class FirebaseNote(
    val uid: String,
    val content: String,
    val createdAt: Timestamp,
    val isCompleted: Boolean,
    val userId: String
)