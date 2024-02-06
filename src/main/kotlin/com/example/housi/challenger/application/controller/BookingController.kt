package com.example.housi.challenger.application.controller

import com.example.housi.challenger.adapter.`in`.routes.BookingRoutes
import com.example.housi.challenger.application.dto.request.SearchPeriodDTO
import com.example.housi.challenger.application.entity.Booking
import com.example.housi.challenger.application.exception.LocalDateException
import com.example.housi.challenger.application.usecase.GetUnavailableDateUsecase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/properties")
class BookingController(val getUnavailableDateUsecase: GetUnavailableDateUsecase): BookingRoutes {

    override fun getBusyDates(@PathVariable(required = true) id:String,
                     @RequestParam(required = false) start: String?,
                     @RequestParam(required = false) end:String?): List<LocalDate> {

        if (start != null && end != null) {
            val searchPeriod = SearchPeriodDTO(LocalDate.parse(start), LocalDate.parse(end))

            if (searchPeriod.start.isAfter(searchPeriod.end)) throw LocalDateException("End date cannot be smaller than start")

            return getUnavailableDateUsecase.findByPeriod(id, searchPeriod)
        }
        return getUnavailableDateUsecase.findAll(id)
    }

    @GetMapping("/{id}")
    override fun getById(@PathVariable id: String): List<Booking> {
        return getUnavailableDateUsecase.findById(id)
    }
}