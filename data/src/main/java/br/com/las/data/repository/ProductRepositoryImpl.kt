package br.com.las.data.repository

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryError
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.data.datasource.remote.api.GetItemsApiService
import br.com.las.data.datasource.remote.dto.mappers.toDomain
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val apiService: GetItemsApiService
) : ProductsRepository {

    override suspend fun searchProducts(query: String): RepositoryResult<List<Product>> {
        return try {
            val response = apiService.searchItems(query, offset = 0, limit = 20)
            response.map { it.toDomain() }.let { products ->
                if (products.isEmpty())
                    RepositoryResult.Failure(RepositoryError(message = "Nenhum produto encontrado"))
                else
                    RepositoryResult.Success(products)
            }
        } catch (e: Exception) {
            RepositoryResult.Failure(RepositoryError(message = e.message ?: "Erro inesperado"))
        }
    }

    override suspend fun getProductDetails(idProduct: Long): RepositoryResult<Product> {
        return try {
            val response = apiService.getProductDetails(idProduct)
            response.toDomain().let { product ->
                RepositoryResult.Success(product)
            }
        } catch (e: Exception) {
            RepositoryResult.Failure(RepositoryError(message = e.message ?: "Erro inesperado"))
        }
    }
}