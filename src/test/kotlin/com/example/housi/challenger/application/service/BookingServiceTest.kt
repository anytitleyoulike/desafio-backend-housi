package com.example.housi.challenger.application.service

import com.example.housi.challenger.application.dto.request.SearchPeriodDTO
import com.example.housi.challenger.application.port.BookingRepositoryPort
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test
import utils.BookingTestUtils
import java.time.LocalDate

class BookingServiceTest {
    private val bookingRepositoryPort = mockk<BookingRepositoryPort>()
    private val bookingService = BookingService(bookingRepositoryPort)

    private val id = "12345"
    private val blockedDates = BookingTestUtils.getBlockedDates()
    private val listOfBookings = BookingTestUtils.getListOfBookings()
    private val listOfBookingsSinceToday = BookingTestUtils.getListOfBookingsSinceToday()
    private val blockedDatesSinceToday = BookingTestUtils.getBlockedDatesSinceToday()
    @Test
    fun `Should get blocked dates with date provided`(){
        val searchPeriodDTO = SearchPeriodDTO(LocalDate.parse("2024-02-01"), LocalDate.parse("2024-02-20"))
        every { bookingRepositoryPort.findBySearchPeriod(id, searchPeriodDTO.start, searchPeriodDTO.end)} returns listOfBookings

        val result = bookingService.findByPeriod(id, searchPeriodDTO)

        assertEquals(result, blockedDates)
    }

    @Test
    fun `Should get blocked dates without date provided`(){
        every { bookingRepositoryPort.findByPropertyIdOrderByCheckInAsc(id) } returns listOfBookingsSinceToday
        val result = bookingService.findPropertyWithoutPeriod(id)

        assertEquals(result, blockedDatesSinceToday)
    }
    @Test
    fun `Should get booking by id with success`() {
        every { bookingRepositoryPort.findByPropertyIdOrderByCheckInAsc(id) } returns listOfBookings

        val result = bookingService.findById(id)
        assertEquals(result, listOfBookings)
    }
}