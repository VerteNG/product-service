package com.example.productservice.domain

import com.example.productservice.domain.product.Product
import com.example.productservice.domain.product.ProductId
import com.example.productservice.domain.product.ProductRepository

class ProductFacade(
    private val productRepository: ProductRepository
) {

    fun addProduct(product: Product): ProductId =
        productRepository.addProduct(product)

    fun getProduct(id: ProductId): Product =
        productRepository.getProduct(id)
}

