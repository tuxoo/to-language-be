package com.tolanguage.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.data.mongodb")
data class MongoProperty(
    val uri: String,
    val username: String,
    val password: String,
    val database: String,
)
