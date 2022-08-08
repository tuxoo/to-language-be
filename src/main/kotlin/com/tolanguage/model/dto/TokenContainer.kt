package com.tolanguage.model.dto

import java.util.*

data class TokenContainer(
    val accessToken: String,
    val refreshToken: UUID
)
