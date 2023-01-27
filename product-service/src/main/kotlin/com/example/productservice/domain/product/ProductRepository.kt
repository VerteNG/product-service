package com.example.productservice.domain.product

interface ProductRepository {

    fun addProduct(product: Product): ProductId

    fun getProduct(id: ProductId): Product
}

data class ProductNotFoundException(val id: ProductId) :
    IllegalArgumentException("Product with id $id does not exist")
