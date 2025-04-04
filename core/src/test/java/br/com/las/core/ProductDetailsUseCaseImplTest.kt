package br.com.las.core

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.result.RepositoryError
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.core.domain.usecase.ProductDetailsUseCaseImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class ProductDetailsUseCaseImplTest {

    private val repository: ProductsRepository = mockk()
    private val useCase = ProductDetailsUseCaseImpl(repository)

    private val fakeProduct = Product(
        id = "2",
        title = "Produto Detalhado",
        price = 249.99,
        currency = "BRL",
        thumbnailUrl = "",
        pictures = listOf(),
        description = "Detalhe do produto",
        attributes = listOf()
    )

    @Test
    fun `should return product details when repository returns success`() = runTest {
        coEvery { repository.getProductDetails("2") } returns RepositoryResult.Success(fakeProduct)

        val result = useCase.invoke("2")

        assert(result is RepositoryResult.Success)
        assertEquals("Produto Detalhado", (result as RepositoryResult.Success).data.title)
    }

    @Test
    fun `should return failure when repository returns failure`() = runTest {
        coEvery { repository.getProductDetails("2") } returns RepositoryResult.Failure(
            error = RepositoryError(
                code = 404,
                message = "Error not found"
            )
        )

        val result = useCase.invoke("2")

        assert(result is RepositoryResult.Failure)
        assertEquals(404, (result as RepositoryResult.Failure).error.code)
    }
}
