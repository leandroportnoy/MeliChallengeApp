package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryResult
import javax.inject.Inject

class GetProductDetailsUseCaseImpl @Inject constructor(
    private val repository: ProductsRepository
): GetProductDetailsUseCase {
    override suspend operator fun invoke(id: Long): RepositoryResult<Product> {
        return repository.getProductDetails(id)
    }
}