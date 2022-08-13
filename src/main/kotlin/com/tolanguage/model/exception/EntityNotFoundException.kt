package com.tolanguage.model.exception

import com.tolanguage.model.error.ErrorType.ENTITY_NOT_FOUND_ERROR

class EntityNotFoundException(text: String) : ApiException(ENTITY_NOT_FOUND_ERROR, text) {
}