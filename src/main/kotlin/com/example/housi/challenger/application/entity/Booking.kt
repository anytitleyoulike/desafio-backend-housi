package com.example.housi.challenger.application.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "booking")
data class Booking (
    @Id
    val id: String,
    val property: Property,
    val checkIn: LocalDate,
    val checkOut: LocalDate,
    val residents: List<Resident>
)


data class Property(
    val id: String,
    val name: String
)

data class Resident(
    val userId: String,
    val name: String,
    val email: String,
    val phone: String
)