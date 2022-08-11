package com.tolanguage.model.entity

import com.tolanguage.model.enums.NoteType
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("note")
data class Note(

    @Id
    val id: ObjectId = ObjectId.get(),
    val type: NoteType,
    val text: String,
    val translation: String,
    val addedAt: Instant = Instant.now(),

    @DBRef
    val course: Course
)
