package com.tolanguage.model.error

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class ErrorType(
    val status: HttpStatus,
    val description: String
) {
    INTERNAL_ERROR(INTERNAL_SERVER_ERROR, "Internal server error"),
    AUTHORIZATION_ERROR(FORBIDDEN, "An error occurred during authorization"),
    AUTHENTICATION_ERROR(UNAUTHORIZED, "An error occurred during authentication"),
    OBJECT_NOT_FOUND_ERROR(NOT_FOUND, "Failed an attempt to get business entity has occurred"),
    VALIDATION_ERROR(BAD_REQUEST, "Request is empty or incorrect")
}