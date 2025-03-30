package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.result.RepositoryResult

interface GetProductDetailsUseCase {
    suspend operator fun invoke(id: Long): RepositoryResult<Product>

}