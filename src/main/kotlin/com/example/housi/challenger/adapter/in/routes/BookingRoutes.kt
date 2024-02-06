package com.example.housi.challenger.adapter.`in`.routes

import com.example.housi.challenger.application.entity.Booking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import java.time.LocalDate

interface BookingRoutes {

    @GetMapping("/{id}/busy-dates")
    fun getBusyDates(@PathVariable(required = true) id:String,
                     @RequestParam(required = false) start: String?,
                     @RequestParam(required = false) end:String?): List<LocalDate>

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): List<Booking>
}