package com.tolanguage.model.dto

import com.tolanguage.model.enums.Language
import java.time.Instant
import javax.validation.constraints.NotBlank

data class CourseFormDto(
    val language: Language,
    @NotBlank(message = "description is blank") val description: String,
    val startedAt: Instant
)
