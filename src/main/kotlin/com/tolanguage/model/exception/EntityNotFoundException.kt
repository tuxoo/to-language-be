package com.tolanguage.model.exception

import com.tolanguage.model.error.ErrorType.OBJECT_NOT_FOUND_ERROR

class EntityNotFoundException(text: String) : ApiException(OBJECT_NOT_FOUND_ERROR, text) {
}