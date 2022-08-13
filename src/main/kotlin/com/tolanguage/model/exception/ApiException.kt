package com.tolanguage.model.exception

import com.tolanguage.model.error.ErrorType

sealed class ApiException(
    val errorType: ErrorType,
    val errorText: String
) : RuntimeException(errorText) {
}
