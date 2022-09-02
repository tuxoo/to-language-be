package com.tolanguage.model.entity

import com.tolanguage.model.enums.MailTopic
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("mail")
data class Mail(

    @Id
    val id: ObjectId = ObjectId.get(),
    val topic: MailTopic,
    val address: String,
    val sentAt: Instant? = null
)
