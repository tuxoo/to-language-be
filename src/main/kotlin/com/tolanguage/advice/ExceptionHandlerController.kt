package com.tolanguage.advice

import com.tolanguage.model.error.ErrorResponse
import com.tolanguage.model.error.ErrorType.*
import com.tolanguage.model.exception.ApiException
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationError(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> =
        e.bindingResult.fieldErrors.map {
            it.defaultMessage
        }.run {
            ResponseEntity.status(BAD_REQUEST).body(
                ErrorResponse(
                    errorType = VALIDATION_ERROR,
                    message = "Validation errors: $this"
                )
            )
        }

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