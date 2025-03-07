package com.example.housi.challenger.application.port

import com.example.housi.challenger.application.entity.Booking
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.time.LocalDate

interface BookingRepositoryPort: MongoRepository<Booking, ObjectId> {
    @Query("{'property.id': ?0, 'checkIn': { \$gt: ?1 }, 'checkOut': { \$lt: ?2 } }")
    fun findBySearchPeriod(propertyId: String, startPeriod: LocalDate, endPeriod: LocalDate): List<Booking>

    fun findByPropertyIdOrderByCheckInAsc(propertyId: String): List<Booking>
}