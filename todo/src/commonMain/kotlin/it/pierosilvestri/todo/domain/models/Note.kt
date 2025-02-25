package it.pierosilvestri.todo.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val id: String,
    val title: String?,
    val content: String,
    val isCompleted: Boolean,
    val createdAt: Long,
    val userId: String
)