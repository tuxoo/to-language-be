package com.tolanguage.model

import com.tolanguage.model.enums.Role
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collation = "user")
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
)