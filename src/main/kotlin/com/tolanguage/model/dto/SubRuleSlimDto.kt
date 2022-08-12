package com.tolanguage.model.dto

import java.time.Instant

data class SubRuleSlimDto(
    val id: String,
    val text: String,
    val addedAt: Instant
)