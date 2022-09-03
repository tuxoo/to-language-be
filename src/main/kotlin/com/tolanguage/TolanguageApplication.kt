package com.tolanguage

import com.tolanguage.config.property.ApplicationProperty
import com.tolanguage.config.property.CacheProperty
import com.tolanguage.config.property.MailServiceProperty
import com.tolanguage.config.property.SessionProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
    ApplicationProperty::class,
    CacheProperty::class,
    SessionProperty::class,
    MailServiceProperty::class
)
class TolanguageApplication

fun main(args: Array<String>) {
    runApplication<TolanguageApplication>(*args)
}
