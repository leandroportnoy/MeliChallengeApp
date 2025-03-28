package br.com.las.data.repository

import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.data.datasource.remote.api.GetItemsApiService

class ProductsRepositoryImpl(
    private val apiService: GetItemsApiService
): ProductsRepository {

    override suspend fun searchProducts(token: String, query: String): RepositoryResult<List<Any>> {
        val authHeader = "Bearer $token"
        val response = apiService.searchItems(authHeader, query, offset = 0, limit = 20)
        return response.result
    }

    override suspend fun getProductDetails(idProduct: Long) {
        TODO("Not yet implemented")
    }
}