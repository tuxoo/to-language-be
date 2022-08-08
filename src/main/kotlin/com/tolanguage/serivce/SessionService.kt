package com.tolanguage.serivce

import com.tolanguage.config.property.ApplicationProperty
import com.tolanguage.config.property.SessionProperty
import com.tolanguage.model.entity.Session
import com.tolanguage.model.entity.User
import com.tolanguage.repository.SessionRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.*

@Service
class SessionService(
    private val sessionRepository: SessionRepository,
    private val applicationProperty: ApplicationProperty,
    private val sessionProperty: SessionProperty
) {

    fun createSession(user: User): UUID =
        with(sessionRepository.findAllByUser(user)) {
            if (size >= sessionProperty.max) {
                sessionRepository.deleteAll(this)
            }
        }.run {
            sessionRepository.insert(
                Session(
                    user = user,
                    expiresAt = Instant.now().plus(applicationProperty.accessTokenTTL)
                )
            ).refreshToken
        }
}