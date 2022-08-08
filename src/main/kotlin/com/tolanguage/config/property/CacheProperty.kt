package com.tolanguage.config.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import java.time.Duration

@ConstructorBinding
@ConfigurationProperties(prefix = "application.cache")
data class CacheProperty(
    val userMaximumSize: Long,
    val userExpiredTime: Duration
)
