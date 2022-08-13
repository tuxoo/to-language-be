package com.tolanguage.config.security

import com.tolanguage.config.property.ApplicationProperty
import com.tolanguage.model.enums.Auth
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.time.Instant
import java.util.*


@Component
class JwtProvider(
    private val property: ApplicationProperty,
) {

    private val HEADER_TYPE = "type";
    private val key = Keys.hmacShaKeyFor(property.jwtSigningKey.toByteArray())
    private val parser = Jwts.parserBuilder().setSigningKey(key).build()

    fun generateToken(subject: String): String = Jwts.builder()
        .setExpiration(Date.from(Instant.now().plus(property.accessTokenTTL)))
        .setSubject(subject)
        .setIssuedAt(Date.from(Instant.now()))
        .signWith(key)
        .setHeaderParam(HEADER_TYPE, Auth.JWT.value)
        .compact()

    fun getUserIdFromToken(token: String): String =
        parser.parseClaimsJws(token).body.subject

    fun validateToken(token: String): Boolean =
        try {
            parser.parseClaimsJws(token).body
                .run {
                    this.expiration.after(Date.from(Instant.now()))
                }
        } catch (e: Exception) {
            false
        }
}