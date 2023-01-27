package com.example.productservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.example.productservice.domain.ProductFacade
import com.example.productservice.domain.product.ProductRepository

@Configuration
class ProductConfig {

    @Bean
    fun productFacade(productRepository: ProductRepository): ProductFacade =
        ProductFacade(productRepository)
}
