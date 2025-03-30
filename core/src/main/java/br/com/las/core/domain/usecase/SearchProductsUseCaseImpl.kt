package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryResult
import javax.inject.Inject

class SearchProductsUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository
): SearchProductsUseCase {
    override suspend operator fun invoke(query: String): RepositoryResult<List<Product>> {
        return repository.searchProducts(query)
    }
}