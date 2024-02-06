package com.example.housi.challenger.application.usecase

import com.example.housi.challenger.application.dto.request.SearchPeriodDTO
import com.example.housi.challenger.application.entity.Booking
import java.time.LocalDate


interface GetUnavailableDateUsecase {

    fun findByPeriod(propertyId: String, searchPeriod: SearchPeriodDTO): List<LocalDate>
    fun findById(propertyId: String): List<Booking>
    fun findPropertyWithoutPeriod(propertyId: String): List<LocalDate>
}