package com.tolanguage.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "mail.service")
data class MailServiceProperty(
    val host: String,
    val port: Int
)
