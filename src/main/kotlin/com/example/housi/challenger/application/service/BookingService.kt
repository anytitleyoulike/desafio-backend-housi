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
        return bookingRepositoryPort.findByPropertyIdOrderByCheckInAsc(propertyId)
    }

    override fun findPropertyWithoutPeriod(propertyId: String): List<LocalDate> {
        val blockedDates = mutableSetOf<LocalDate>()

        var currentDate: LocalDate = LocalDate.now()
        val bookings = bookingRepositoryPort.findByPropertyIdOrderByCheckInAsc(propertyId)
        for (booking in bookings) {
            while (currentDate <= booking.checkOut) {
                if(currentDate >= booking.checkIn && currentDate <= booking.checkOut) {
                    blockedDates.add(currentDate)
                }
                currentDate = currentDate.plusDays(1)
            }

        }
        return blockedDates.sorted()
    }
}
