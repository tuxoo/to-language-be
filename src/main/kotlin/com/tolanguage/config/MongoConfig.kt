package com.tolanguage.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.tolanguage.config.property.MongoProperty
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.tolanguage.repository"])
class MongoConfig(
    private val property: MongoProperty
) : AbstractMongoClientConfiguration() {

    override fun getDatabaseName(): String = property.database

    override fun mongoClient(): MongoClient = MongoClientSettings.builder()
        .applyConnectionString(ConnectionString(property.uri))
        .build().run {
            MongoClients.create(this)

        }
}