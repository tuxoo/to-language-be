package com.tolanguage.config.property

import com.tolanguage.util.HashUtils
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.context.annotation.Bean
import java.time.Duration

@ConstructorBinding
@ConfigurationProperties(prefix = "application")
data class ApplicationProperty(
    val url: String,
    val apiPath: String,
    val hashSalt: String,
    val jwtSigningKey: String,
    val accessTokenTTL: Duration
) {

    @Bean
    fun hashSalt(): HashUtils {
        return HashUtils.also {
            it.salt = hashSalt
        }
    }
}
