package br.com.las.data.repository

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryError
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.data.datasource.assets.LocalJsonProductDataSource
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val dataSource: LocalJsonProductDataSource
) : ProductsRepository {

    override suspend fun getProductsList(query: String): RepositoryResult<List<Product>> {
        return try {
            val products = dataSource.loadProducts()
            if (products.isEmpty())
                RepositoryResult.Failure(RepositoryError(message = "Nenhum produto encontrado"))
            else
                RepositoryResult.Success(products)
        } catch (e: Exception) {
            RepositoryResult.Failure(RepositoryError(message = e.message ?: "Erro inesperado"))
        }
    }

    override suspend fun getProductDetails(idProduct: Long): RepositoryResult<Product> {
        //TODO implement this method
        return RepositoryResult.Failure(RepositoryError(message = "Método não implementado"))
//        return try {
//            val response = apiService.getProductDetails(idProduct)
//            response.toDomain().let { product ->
//                RepositoryResult.Success(product)
//            }
//        } catch (e: Exception) {
//            RepositoryResult.Failure(RepositoryError(message = e.message ?: "Erro inesperado"))
//        }
    }
}