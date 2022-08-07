package com.tolanguage.model.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.tolanguage.model.enums.Role
import java.time.Instant
import java.util.*

data class UserDto(
    val id: UUID,
    val name: String,
    @JsonProperty("email")
    val loginEmail: String,
    val registeredAt: Instant,
    val visitedAt: Instant,
    val role: Role,
    val isEnabled: Boolean
)