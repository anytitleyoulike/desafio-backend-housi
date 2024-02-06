package com.example.housi.challenger.infrastructure

import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.stereotype.Component

@Component
class MongoConfiguration(
    @Value("\${spring.data.mongodb.host}") val host: String,
    @Value("\${spring.data.mongodb.port}") val port: String,
    @Value("\${spring.data.mongodb.database}") val database: String
): AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String {
        return database
    }

    override fun mongoClient(): MongoClient {
        return MongoClients.create("mongodb://${host}:${port}/${database}")
    }

}