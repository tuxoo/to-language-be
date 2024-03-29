package com.tolanguage.model.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("rule")
data class Rule(

    @Id
    val id: ObjectId = ObjectId.get(),
    val title: String,
    val addedAt: Instant = Instant.now(),

    @DBRef
    val course: Course
)
