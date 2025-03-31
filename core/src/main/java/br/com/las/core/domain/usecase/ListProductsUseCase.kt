package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.result.RepositoryResult

interface ListProductsUseCase {
    suspend operator fun invoke(query: String): RepositoryResult<List<Product>>
}
