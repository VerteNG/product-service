package com.example.productservice.api.service

import org.springframework.stereotype.Service
import com.example.productservice.api.endpoint.AddProductRequest
import com.example.productservice.api.endpoint.AddProductResponse
import com.example.productservice.api.endpoint.GetProductResponse
import com.example.productservice.api.endpoint.PriceDto
import com.example.productservice.domain.ProductFacade
import com.example.productservice.domain.product.Currency
import com.example.productservice.domain.product.Price
import com.example.productservice.domain.product.Product
import com.example.productservice.domain.product.toProductId
import java.util.UUID

@Service
class ProductApiService(
    private val productFacade: ProductFacade
) {
    fun addProduct(productRequest: AddProductRequest): AddProductResponse =
        productFacade.addProduct(productRequest.toProduct())
            .let { AddProductResponse(productId = it.toString()) }

    fun getProduct(id: String): GetProductResponse =
        productFacade.getProduct(id.toProductId())
            .toGetProductResponse()

    private fun AddProductRequest.toProduct() =
        Product(
            id = UUID.randomUUID(),
            name = name,
            description = description,
            price = price.toDomainPrice(),
            context = context
        )

    private fun PriceDto.toDomainPrice() =
        Price(
            amount = amount,
            currency = Currency.valueOf(currency)
        )

    private fun Product.toGetProductResponse() =
        GetProductResponse(
            id = id.toString(),
            name = name,
            description = description,
            price = price.toPriceDto(),
            context = context
        )

    private fun Price.toPriceDto() =
        PriceDto(
            amount = amount,
            currency = currency.name
        )
}

