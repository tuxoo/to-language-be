package com.tolanguage.model.dto

import com.tolanguage.model.enums.NoteType

data class NoteFormDto(
    val type: NoteType,
    val text: String,
    val translation: String? = null,
    val source: String,
)