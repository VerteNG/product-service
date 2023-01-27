package com.example.productservice.infrastructure.mongo

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.stereotype.Repository
import com.example.productservice.domain.product.Currency
import com.example.productservice.domain.product.Price
import com.example.productservice.domain.product.Product
import com.example.productservice.domain.product.ProductId
import com.example.productservice.domain.product.ProductNotFoundException
import com.example.productservice.domain.product.ProductRepository
import com.example.productservice.domain.product.toProductId
import java.math.BigDecimal

@Repository
class MongoProductRepository(
    private val mongoTemplate: MongoTemplate
) : ProductRepository {

    companion object {
        private const val PRODUCT_ID_FIELD = "_id"
    }

    override fun addProduct(product: Product): ProductId =
        mongoTemplate
            .insert(product.toMongoProduct())
            .id.toProductId()

    override fun getProduct(id: ProductId): Product =
        findMongoProduct(id)
            ?.toDomainProduct()
            ?: throw ProductNotFoundException(id)

    private fun Product.toMongoProduct() =
        MongoProduct(
            id = id.toString(),
            name = name,
            description = description,
            price = price.toMongoPrice(),
            context = context
        )

    private fun findMongoProduct(id: ProductId): MongoProduct? =
        where(PRODUCT_ID_FIELD).isEqualTo(id.toString())
            .toQuery()
            .let { mongoTemplate.findOne(it, MongoProduct::class.java) }

    private fun Price.toMongoPrice() =
        MongoPrice(
            amount = amount,
            currency = currency.name
        )

    private fun Criteria.toQuery() = Query.query(this)
}

@Document("products")
data class MongoProduct(
    @Id val id: String,
    val name: String,
    val description: String,
    val price: MongoPrice,
    val context: Map<String, String>?
) {

    fun toDomainProduct(): Product = Product(
        id = id.toProductId(),
        name = name,
        description = description,
        price = price.toDomainPrice(),
        context = context
    )
}

data class MongoPrice(
    val amount: BigDecimal,
    val currency: String
) {

    fun toDomainPrice(): Price = Price(
        amount = amount,
        currency = Currency.valueOf(currency)
    )
}
