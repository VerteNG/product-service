package com.example.productservice.domain.product

import java.math.BigDecimal
import java.util.UUID

typealias ProductId = UUID

data class Product(
    val id: ProductId,
    val name: String,
    val description: String,
    val price: Price,
    val context: Map<String, String>?
)

data class Price(
    val amount: BigDecimal,
    val currency: Currency
) {

    init {
        require(amount.scale() <= 2) {
            "Price amount of product cannot have more than 2 decimal places"
        }
    }
}

enum class AssetType {
    CREDIT,
    DEBIT
}

enum class Currency {
    PLN
}

fun String.toProductId(): UUID = UUID.fromString(this)
