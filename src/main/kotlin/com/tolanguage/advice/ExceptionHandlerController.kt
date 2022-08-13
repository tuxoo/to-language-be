package com.tolanguage.advice

import com.tolanguage.model.error.ErrorResponse
import com.tolanguage.model.error.ErrorType.AUTHORIZATION_ERROR
import com.tolanguage.model.error.ErrorType.INTERNAL_ERROR
import com.tolanguage.model.exception.ApiException
import org.springframework.http.HttpStatus.FORBIDDEN
import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ExceptionHandlerController {

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    fun handleGlobalError(e: Exception): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(INTERNAL_SERVER_ERROR).body(
            ErrorResponse(
                errorType = INTERNAL_ERROR,
                message = e.message ?: "Internal server error"
            )
        )

    @ExceptionHandler(ApiException::class)
    fun handleBusinessError(e: ApiException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(e.errorType.status).body(
            ErrorResponse(
                errorType = e.errorType,
                message = e.errorText
            )
        )

    @ResponseStatus(FORBIDDEN)
    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedError(e: AccessDeniedException): ResponseEntity<ErrorResponse> =
        ResponseEntity.status(FORBIDDEN).body(
            ErrorResponse(
                errorType = AUTHORIZATION_ERROR,
                message = e.message ?: "Error occurred authorization"
            )
        )
}