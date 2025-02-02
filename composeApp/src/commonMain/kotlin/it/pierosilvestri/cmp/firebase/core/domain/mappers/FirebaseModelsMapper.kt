package it.pierosilvestri.cmp.firebase.core.domain.mappers

import it.pierosilvestri.cmp.firebase.core.data.models.firebase.FirebaseNote
import it.pierosilvestri.cmp.firebase.core.domain.models.Note

fun FirebaseNote.toNote(): Note =
    Note(
        id = uid,
        content = content,
        isCompleted = isCompleted,
        createdAt = 0L
    )