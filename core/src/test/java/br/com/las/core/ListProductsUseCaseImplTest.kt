package br.com.las.core

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryError
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.core.domain.usecase.ListProductsUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ListProductsUseCaseImplTest {

    private val repository: ProductsRepository = mockk()
    private val useCase = ListProductsUseCaseImpl(repository)

    private val fakeProductList = listOf(
        Product(
            id = "1",
            title = "Produto A",
            price = 100.0,
            currency = "BRL",
            thumbnailUrl = "",
            pictures = emptyList(),
            description = "Descrição A",
            attributes = emptyList()
        )
    )

    @Test
    fun `should return product list when repository returns success`() = runTest {
        coEvery { repository.getProductsList("notebook") } returns RepositoryResult.Success(fakeProductList)

        val result = useCase.invoke("notebook")

        assert(result is RepositoryResult.Success)
        assertEquals(1, (result as RepositoryResult.Success).data.size)
    }

    @Test
    fun `should return failure when repository returns failure`() = runTest {
        coEvery { repository.getProductsList("notebook") } returns RepositoryResult.Failure(
            error = RepositoryError(
                code = 404,
                message = "Erro interno"
            )
        )

        val result = useCase.invoke("notebook")

        assert(result is RepositoryResult.Failure)
        assertEquals("Erro interno", (result as RepositoryResult.Failure).error.message)
    }
}
