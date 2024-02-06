package com.example.housi.challenger

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration
import org.springframework.boot.runApplication


@SpringBootApplication
@EnableAutoConfiguration(exclude=[MongoAutoConfiguration::class, MongoDataAutoConfiguration::class])
class ChallengerApplication

fun main(args: Array<String>) {
	runApplication<ChallengerApplication>(*args)
}
