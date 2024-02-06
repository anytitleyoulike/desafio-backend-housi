package com.example.housi.challenger.application.service

import com.example.housi.challenger.application.dto.request.SearchPeriodDTO
import com.example.housi.challenger.application.entity.Booking
import com.example.housi.challenger.application.port.BookingRepositoryPort
import com.example.housi.challenger.application.usecase.GetUnavailableDateUsecase
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class BookingService(private val bookingRepositoryPort: BookingRepositoryPort): GetUnavailableDateUsecase {
    override fun findByPeriod(propertyId: String, searchPeriod: SearchPeriodDTO): List<LocalDate> {
        val blockedDates = mutableSetOf<LocalDate>()
        var currentDate = searchPeriod.start

        val bookings = bookingRepositoryPort.findBySearchPeriod(propertyId, searchPeriod.start, searchPeriod.end)

        while (currentDate <= searchPeriod.end) {
            for(booking in bookings)
                if(currentDate >= booking.checkIn && currentDate <= booking.checkOut) {
                    blockedDates.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
        }
        return blockedDates.sorted()
    }

    override fun findById(propertyId: String): List<Booking> {
        return bookingRepositoryPort.findByPropertyId(propertyId)
    }

    override fun findAll(propertyId: String): List<LocalDate> {
        val blockedDates = mutableSetOf<LocalDate>()
        val bookings = bookingRepositoryPort.findByPropertyId(propertyId)

        var startDate: LocalDate
        for (booking in bookings) {
            startDate = booking.checkIn
            while (startDate <= booking.checkOut) {
                blockedDates.add(startDate)
                startDate = startDate.plusDays(1)
            }
        }
        return blockedDates.sorted()
    }
}
