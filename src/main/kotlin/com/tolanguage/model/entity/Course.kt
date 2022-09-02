package com.tolanguage.model.entity

import com.tolanguage.model.enums.Language
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("course")
data class Course(

    @Id
    val id: ObjectId = ObjectId.get(),
    val language: Language,
    val description: String,
    val startedAt: Instant = Instant.now(),
    val lastModifiedAt: Instant = Instant.now(),

    @DBRef
    val user: User
)
