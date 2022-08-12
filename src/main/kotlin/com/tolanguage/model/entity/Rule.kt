package com.tolanguage.model.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document

@Document("rule")
data class Rule(

    @Id
    val id: ObjectId = ObjectId.get(),
    val title: String,

    @DBRef
    val course: Course
)
