package com.tolanguage.config.security

import com.tolanguage.model.enums.Auth.AUTHORIZATION
import com.tolanguage.model.enums.Auth.BEARER
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

@Component
class JwtFilter(
    private val jwtProvider: JwtProvider,
    private val appUserDetailsService: AppUserDetailsService
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token = getTokenFromRequest(request as HttpServletRequest?)

        if (token.isNotEmpty() && jwtProvider.validateToken(token)) {
            SecurityContextHolder.getContext().authentication = jwtProvider.getUserIdFromToken(token).run {
                appUserDetailsService.loadUserByUserId(this)
            }.run {
                UsernamePasswordAuthenticationToken(this, null, this.authorities)
            }
        }
        chain!!.doFilter(request, response)
    }

    private fun getTokenFromRequest(request: HttpServletRequest?): String {
        val bearer = request?.getHeader(AUTHORIZATION.value) ?: ""
        return if (bearer.isNotBlank() && bearer.startsWith(BEARER.value)) {
            bearer.substring(BEARER.length)
        } else ""
    }
}