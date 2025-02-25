package it.pierosilvestri.todo.domain.mappers

import dev.gitlive.firebase.firestore.Timestamp
import it.pierosilvestri.todo.data.models.firebase.FirebaseNote
import it.pierosilvestri.todo.domain.models.Note
import kotlinx.datetime.Instant

fun FirebaseNote.toNote(): Note {

    // Convert to epoch milliseconds
    val epochMilliseconds = (this.createdAt.seconds * 1000) + (this.createdAt.nanoseconds / 1_000_000)

    return Note(
        id = uid,
        title = title ?: "",
        content = content,
        isCompleted = isCompleted,
        createdAt = epochMilliseconds,
        userId = userId
    )
}



fun Note.toFirebaseNote(): FirebaseNote {
    // Convert to kotlinx-datetime Instant
    val instant = Instant.fromEpochMilliseconds(this.createdAt)

    // Convert to Firebase Timestamp (seconds and nanoseconds)
    val seconds = instant.epochSeconds
    val nanoseconds = (this.createdAt % 1000) * 1_000_000

    // Create the Firebase Timestamp
    val firebaseTimestamp = Timestamp(seconds, nanoseconds.toInt())

    return FirebaseNote(
        uid = id,
        title = title,
        content = content,
        isCompleted = isCompleted,
        userId = userId,
        createdAt = firebaseTimestamp
    )
}



