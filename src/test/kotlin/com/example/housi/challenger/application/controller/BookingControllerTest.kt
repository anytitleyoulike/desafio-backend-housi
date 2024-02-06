package com.example.housi.challenger.application.controller

import com.example.housi.challenger.application.controller.BookingController
import com.example.housi.challenger.application.entity.Booking
import com.example.housi.challenger.application.entity.Property
import com.example.housi.challenger.application.entity.Resident
import com.example.housi.challenger.application.exception.LocalDateException
import com.example.housi.challenger.application.usecase.GetUnavailableDateUsecase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows

import java.time.LocalDate

class BookingControllerTest {
    private val getUnavailableDateUsecase = mockk<GetUnavailableDateUsecase>()
    private val bookingController = BookingController(getUnavailableDateUsecase)

    private val id = "12345"
    private val bookings = Booking(
        id=id,
        checkIn = LocalDate.now(),
        checkOut = LocalDate.now().plusDays(2),
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
    fun `Should get bookings by property id`(){
        val listBooking = listOf(bookings)
        every { getUnavailableDateUsecase.findById(id) } returns listBooking

        val bookListMocked = bookingController.getById(id)

        assertEquals(id, bookListMocked.first().id)
    }

    @Test
    fun `Should get blocked dates with date provided`(){
        val start= "2024-02-01"
        val end= "2024-02-06"

        every { getUnavailableDateUsecase.findByPeriod(id, any()) } returns blockedDates
        val result = bookingController.getBusyDates(id, start, end)

        assertEquals(blockedDates, result)
    }

    @Test
    fun `Should get blocked dates with no date provided`(){
        every { getUnavailableDateUsecase.findPropertyWithoutPeriod(id) } returns blockedDates
        val result = bookingController.getBusyDates(id, null, null)

        assertEquals(blockedDates, result)
    }

    @Test
    fun `Should throw exception if end date is lesser then start`() {
        assertThrows<LocalDateException> {
            bookingController.getBusyDates(id = "12343", start = "2024-02-02", end = "2024-01-30")
        }
    }
}