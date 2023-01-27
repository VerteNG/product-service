package com.example.productservice.api.error

data class ErrorResponse(
    val statusCode: Int,
    val message: String?
)
