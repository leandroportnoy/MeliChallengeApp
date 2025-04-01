package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryResult
import javax.inject.Inject

class ProductDetailsUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository
): ProductDetailsUseCase {
    override suspend operator fun invoke(id: String): RepositoryResult<Product> {
        return repository.getProductDetails(id)
    }
}