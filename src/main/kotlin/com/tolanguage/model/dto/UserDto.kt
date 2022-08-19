package com.tolanguage.model.dto

import java.time.Instant

data class UserDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val registeredAt: Instant
)
