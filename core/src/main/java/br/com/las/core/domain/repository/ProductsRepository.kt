package br.com.las.core.domain.repository

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.result.RepositoryResult

interface ProductsRepository {
    suspend fun getProductsList(query: String): RepositoryResult<List<Product>>
    suspend fun getProductDetails(idProduct: Long): RepositoryResult<Product>
}