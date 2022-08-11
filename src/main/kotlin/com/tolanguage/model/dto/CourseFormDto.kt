package com.tolanguage.model.dto

import com.tolanguage.model.enums.Language
import java.time.Instant

data class CourseFormDto(
    val language: Language,
    val description: String,
    val startedAt: Instant
)
