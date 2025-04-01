package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.result.RepositoryResult

interface ProductDetailsUseCase {
    suspend operator fun invoke(id: String): RepositoryResult<Product>

}