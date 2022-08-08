package com.tolanguage.model.entity

import com.tolanguage.model.enums.Role
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("user")
data class User(

    @Id
    val id: ObjectId = ObjectId.get(),
    val firstName: String,
    val lastName: String,
    val email: String,
    val passwordHash: String,
    val registeredAt: Instant = Instant.now(),
    val visitedAt: Instant = Instant.now(),
    val role: Role = Role.USER,
    val isActivated: Boolean = false,

    @DBRef
    val sessions: List<Session> = emptyList()
)