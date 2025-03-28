package br.com.las.core.domain.repository

import br.com.las.core.domain.result.RepositoryResult

interface ProductsRepository {
    suspend fun searchProducts(token: String, query: String): RepositoryResult<List<Any>>;
    suspend fun getProductDetails(idProduct: Long);
}