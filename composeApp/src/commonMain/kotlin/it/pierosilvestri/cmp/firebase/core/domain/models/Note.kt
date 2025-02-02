package it.pierosilvestri.cmp.firebase.core.domain.models

import dev.gitlive.firebase.firestore.Timestamp
import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: String,
    val content: String,
    val isCompleted: Boolean,
    val createdAt: Long,
)