package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryResult
import javax.inject.Inject

class ListProductsUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository
): ListProductsUseCase {
    override suspend fun invoke(query: String): RepositoryResult<List<Product>> {
        return repository.getProductsList(query)
    }
}