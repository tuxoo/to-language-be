package com.tolanguage.model.error

import java.time.Instant

data class ErrorResponse(
    val errorType: ErrorType,
    val message: String,
    val errorTime: Instant = Instant.now()
)
