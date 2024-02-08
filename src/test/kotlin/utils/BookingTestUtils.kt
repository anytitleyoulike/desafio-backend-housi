package utils

import com.example.housi.challenger.application.entity.Booking
import com.example.housi.challenger.application.entity.Property
import com.example.housi.challenger.application.entity.Resident
import java.time.LocalDate

object BookingTestUtils
 {
    fun getBooking(): Booking {
        return Booking(
            id="12345",
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
    }
     fun getListOfBookings(): List<Booking> {
         val anotherBooking = getBooking().copy(
             checkIn = LocalDate.parse("2024-02-15"),
             checkOut = LocalDate.parse("2024-02-20")
         )
         return listOf(getBooking(), anotherBooking)
     }

     fun getBlockedDates(): List<LocalDate> {
         return listOf<LocalDate>(
             LocalDate.parse("2024-02-01"),
             LocalDate.parse("2024-02-02"),
             LocalDate.parse("2024-02-03"),
             LocalDate.parse("2024-02-15"),
             LocalDate.parse("2024-02-16"),
             LocalDate.parse("2024-02-17"),
             LocalDate.parse("2024-02-18"),
             LocalDate.parse("2024-02-19"),
             LocalDate.parse("2024-02-20"),
         )
     }

     fun getListOfBookingsSinceToday(): List<Booking> {
         val anotherBooking = getBooking().copy(
             checkIn = LocalDate.now(),
             checkOut = LocalDate.now().plusDays(5)
         )
         return listOf(getBooking(), anotherBooking)
     }

     fun getBlockedDatesSinceToday(): List<LocalDate> {
         var currentDate = LocalDate.now()
         val plusDate = currentDate.plusDays(5)
         val blockedDates = mutableListOf<LocalDate>()
         while (currentDate <= plusDate) {
             blockedDates.add(currentDate)
             currentDate = currentDate.plusDays(1)
         }
         return blockedDates
     }

}