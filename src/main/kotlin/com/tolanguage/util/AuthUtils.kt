package com.tolanguage.util

import com.tolanguage.config.security.AppUserDetails
import com.tolanguage.model.exception.AuthorizationException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import kotlin.reflect.cast

object AuthUtils {

    private fun authentication(): Authentication = SecurityContextHolder.getContext().authentication

    fun getCurrentUser(): AppUserDetails = Optional.ofNullable(authentication())
        .map(Authentication::getPrincipal)
        .filter(AppUserDetails::class::isInstance)
        .map(AppUserDetails::class::cast)
        .orElseThrow {
            AuthorizationException("Forbidden user")
        }
}