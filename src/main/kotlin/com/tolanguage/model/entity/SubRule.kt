package com.tolanguage.model.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("sub_rule")
data class SubRule(

    @Id
    val id: ObjectId = ObjectId.get(),
    val text: String,
    val addedAt: Instant = Instant.now(),

    @DBRef
    val rule: Rule
)
