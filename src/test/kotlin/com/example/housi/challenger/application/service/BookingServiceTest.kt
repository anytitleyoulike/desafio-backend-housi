package com.example.housi.challenger.application.service

import com.example.housi.challenger.application.dto.request.SearchPeriodDTO
import com.example.housi.challenger.application.entity.Booking
import com.example.housi.challenger.application.entity.Property
import com.example.housi.challenger.application.entity.Resident
import com.example.housi.challenger.application.port.BookingRepositoryPort
import com.example.housi.challenger.application.service.BookingService
import io.mockk.every
import io.mockk.mockk
import org.junit.Test
import java.time.LocalDate
import org.junit.Assert.assertEquals
class BookingServiceTest {
    private val bookingRepositoryPort = mockk<BookingRepositoryPort>()
    private val bookingService = BookingService(bookingRepositoryPort)

    private val id = "12345"
    val bookings = Booking(
        id=id,
        checkIn = LocalDate.parse("2024-02-01"),
        checkOut = LocalDate.parse("2024-02-03"),
        property = Property(
            id = "abcd",
            name= "Residencia 1"
        ),
        residents = listOf(
            Resident(userId = "09876",
                name = "User Test",
                email = "user@test.com",
                phone = "99 99999-9999")
        )
    )

    val blockedDates = listOf<LocalDate>(
        LocalDate.parse("2024-02-01"),
        LocalDate.parse("2024-02-02"),
        LocalDate.parse("2024-02-03")
    )

    @Test
    fun `Should get blocked dates with date provided`(){
        val searchPeriodDTO = SearchPeriodDTO(LocalDate.parse("2024-02-01"), LocalDate.parse("2024-02-06"))
        every { bookingRepositoryPort.findBySearchPeriod(id, searchPeriodDTO.start, searchPeriodDTO.end)} returns listOf(bookings)
                listOf(bookings)
        val result = bookingService.findByPeriod(id, searchPeriodDTO)

        assertEquals(result, blockedDates)
    }

    @Test
    fun `Should get blocked dates without date provided`(){
        every { bookingRepositoryPort.findByPropertyId(id) } returns listOf(bookings)
        val result = bookingService.findPropertyWithoutPeriod(id)

        assertEquals(result, blockedDates)
    }
    @Test
    fun `Should get booking by id with success`() {
        val listBooking = listOf(bookings)
        every { bookingRepositoryPort.findByPropertyId(id) } returns listBooking

        val result = bookingService.findById(id)
        assertEquals(result, listBooking)
    }
}