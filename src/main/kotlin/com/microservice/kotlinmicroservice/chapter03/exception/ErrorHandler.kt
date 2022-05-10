package com.microservice.kotlinmicroservice.chapter03.exception

import com.fasterxml.jackson.core.JsonParseException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice   // Spring Context에 등록됨
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)    // 처리할 예외 유형
    fun JsonParseExceptionHandler(servletRequest: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("JSON Error", exception.message ?: "Invalid JSON"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(CustomerNotFoundException::class)
    fun CustomerNotFoundExceptionHandler(servletRequest: HttpServletRequest,
                                         exception: Exception): ResponseEntity<ErrorResponse> {
        return ResponseEntity(ErrorResponse("Customer Not Found", exception.message!!), HttpStatus.NOT_FOUND)
    }
}