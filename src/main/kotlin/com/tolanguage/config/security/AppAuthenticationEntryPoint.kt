package com.tolanguage.config.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.tolanguage.model.error.ErrorResponse
import com.tolanguage.model.error.ErrorType.AUTHENTICATION_ERROR
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AppAuthenticationEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {
        response!!.contentType = MediaType.APPLICATION_JSON_VALUE
        response.status = HttpServletResponse.SC_UNAUTHORIZED

        val printWriter = response.writer
        printWriter.write(
            objectMapper.writeValueAsString(
                ErrorResponse(
                    AUTHENTICATION_ERROR,
                    AUTHENTICATION_ERROR.description
                )
            )
        )
        printWriter.flush()
    }
}