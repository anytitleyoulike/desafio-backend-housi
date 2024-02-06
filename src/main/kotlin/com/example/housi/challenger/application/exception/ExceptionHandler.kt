package com.example.housi.challenger.application.exception

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ExceptionHandler {
    @ExceptionHandler(LocalDateException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleLocalDateValidation(exception: LocalDateException, request: HttpServletRequest): ExceptionResponse {
        return ExceptionResponse(
            status = HttpStatus.BAD_REQUEST.value(),
            error = HttpStatus.BAD_REQUEST.name,
            message = exception.message,
            path = request.servletPath
        )
    }

    @ExceptionHandler(EmptyResultException::class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun handleBookingExceptionValidation(exception: EmptyResultException, request: HttpServletRequest): ExceptionResponse {
        return ExceptionResponse(
            status = HttpStatus.ACCEPTED.value(),
            error = HttpStatus.ACCEPTED.name,
            message = exception.message,
            path = request.servletPath
        )
    }
}

class ExceptionResponse (
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val status: Int,
    val error: String,
    val message: String?,
    val path: String
)
