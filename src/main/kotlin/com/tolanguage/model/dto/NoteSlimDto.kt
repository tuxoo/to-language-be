package com.tolanguage.model.dto

import com.tolanguage.model.enums.NoteType
import java.time.Instant

data class NoteSlimDto(
    val id: String,
    val type: NoteType,
    val text: String,
    val translation: String? = null,
    val addedAt: Instant
)