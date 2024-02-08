package com.example.housi.challenger.application.controller

import com.example.housi.challenger.application.exception.LocalDateException
import com.example.housi.challenger.application.usecase.GetUnavailableDateUsecase
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.jupiter.api.assertThrows
import utils.BookingTestUtils

class BookingControllerTest {
    private val getUnavailableDateUsecase = mockk<GetUnavailableDateUsecase>()
    private val bookingController = BookingController(getUnavailableDateUsecase)
    private val bookings = BookingTestUtils.getBooking()
    private val listOfBookings = BookingTestUtils.getListOfBookings()
    private val id = "12345"

    val blockedDates = BookingTestUtils.getBlockedDates()
    @Test
    fun `Should get bookings by property id`(){
        every { getUnavailableDateUsecase.findById(id) } returns listOfBookings

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