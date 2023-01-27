package com.example.productservice.api.endpoint

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL
import org.springframework.http.HttpStatus.BAD_REQUEST
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.OK
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import com.example.productservice.api.error.ErrorResponse
import com.example.productservice.api.service.ProductApiService
import com.example.productservice.domain.product.ProductNotFoundException
import java.math.BigDecimal

@RestController
class ProductEndpoint(
    private val productApiService: ProductApiService
) {

    @PostMapping("/products/add")
    @ResponseStatus(CREATED)
    fun addProduct(
        @RequestBody productRequest: AddProductRequest
    ): AddProductResponse =
        productApiService.addProduct(productRequest)

    @GetMapping("/products/{productId}")
    @ResponseStatus(OK)
    fun getProduct(
        @PathVariable productId: String
    ): GetProductResponse =
        productApiService.getProduct(productId)

    @ExceptionHandler
    @ResponseStatus(BAD_REQUEST)
    fun handleProductNotFoundException(ex: ProductNotFoundException) = ErrorResponse(
        statusCode = BAD_REQUEST.value(),
        message = ex.message
    )
}

data class AddProductRequest(
    val name: String,
    val description: String,
    val price: PriceDto,
    val context: Map<String, String>?
)

data class AddProductResponse(
    val productId: String
)

@JsonInclude(NON_NULL)
data class GetProductResponse(
    val id: String,
    val name: String,
    val description: String,
    val price: PriceDto,
    val context: Map<String, String>?
)

data class PriceDto(
    val amount: BigDecimal,
    val currency: String
)
