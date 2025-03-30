package br.com.las.core.domain.usecase

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.result.RepositoryResult

interface SearchProductsUseCase {
    suspend operator fun invoke(query: String): RepositoryResult<List<Product>>
}
