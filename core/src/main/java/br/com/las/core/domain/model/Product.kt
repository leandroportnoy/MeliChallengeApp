package br.com.las.core.domain.model

data class Product(
    val id: String,
    val title: String,
    val price: Double,
    val thumbnailUrl: String,
    val storeName: String?,
    val city: String?,
    val state: String?
)