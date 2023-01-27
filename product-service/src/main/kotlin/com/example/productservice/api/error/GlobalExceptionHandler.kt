package com.example.productservice.api.error

import org.springframework.core.Ordered.HIGHEST_PRECEDENCE
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import com.example.productservice.api.error.GlobalExceptionHandler.Companion.ORDER

@ControllerAdvice
@Order(ORDER)
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    companion object {
        const val ORDER = HIGHEST_PRECEDENCE
    }

    @ExceptionHandler(IllegalArgumentException::class)
    @ResponseStatus(BAD_REQUEST)
    fun handleIllegalArgumentException(ex: IllegalArgumentException): ResponseEntity<ErrorResponse> = ErrorResponse(
        statusCode = BAD_REQUEST.value(),
        message = ex.message
    ).let { ResponseEntity.status(BAD_REQUEST).body(it) }
}
