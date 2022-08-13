package com.tolanguage.model.exception

import com.tolanguage.model.error.ErrorType.AUTHORIZATION_ERROR

class AuthorizationException(text: String) : ApiException(AUTHORIZATION_ERROR, text) {
}