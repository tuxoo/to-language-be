package com.tolanguage

import com.tolanguage.config.property.MongoProperty
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
    MongoProperty::class
)
class TolanguageApplication

fun main(args: Array<String>) {
    runApplication<TolanguageApplication>(*args)
}
