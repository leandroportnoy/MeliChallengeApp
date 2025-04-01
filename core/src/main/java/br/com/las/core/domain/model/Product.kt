package br.com.las.core.domain.model

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val currency: String,
    val thumbnailUrl: String,
    val pictures: List<String>,
    val description: String,
    val attributes: List<ProductAttribute>
)

data class ProductAttribute(
    val name: String,
    val value: String
)