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

     fun getBlockedDates(): List<LocalDate> {
         return listOf<LocalDate>(
             LocalDate.parse("2024-02-01"),
             LocalDate.parse("2024-02-02"),
             LocalDate.parse("2024-02-03")
         )
     }

}