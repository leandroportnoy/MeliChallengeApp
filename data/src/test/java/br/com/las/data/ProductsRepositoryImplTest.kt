package br.com.las.data

import br.com.las.core.domain.model.Product
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.data.datasource.assets.LocalJsonProductDataSource
import br.com.las.data.repository.ProductsRepositoryImpl
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class ProductsRepositoryImplTest {

    private lateinit var dataSource: LocalJsonProductDataSource
    private lateinit var repository: ProductsRepositoryImpl

    private val fakeProduct = Product(
        id = "1",
        title = "Produto Teste",
        price = 10.0,
        currency = "BRL",
        thumbnailUrl = "url",
        pictures = listOf("img1"),
        description = "Descrição",
        attributes = emptyList()
    )

    @Before
    fun setup() {
        dataSource = mockk()
        repository = ProductsRepositoryImpl(dataSource)
    }

    @Test
    fun `getProductsList should return success when products are available`() = runTest {
        coEvery { dataSource.loadProducts() } returns listOf(fakeProduct)

        val result = repository.getProductsList("")

        assertTrue(result is RepositoryResult.Success)
        assertEquals(1, (result as RepositoryResult.Success).data.size)
        assertEquals("Produto Teste", result.data[0].title)
    }

    @Test
    fun `getProductsList should return failure when product list is empty`() = runTest {
        coEvery { dataSource.loadProducts() } returns emptyList()

        val result = repository.getProductsList("")

        assertTrue(result is RepositoryResult.Failure)
        assertEquals("Nenhum produto encontrado", (result as RepositoryResult.Failure).error.message)
    }

    @Test
    fun `getProductsList should return failure when exception is thrown`() = runTest {
        coEvery { dataSource.loadProducts() } throws RuntimeException("Erro de leitura")

        val result = repository.getProductsList("")

        assertTrue(result is RepositoryResult.Failure)
        assertEquals("Erro de leitura", (result as RepositoryResult.Failure).error.message)
    }

    @Test
    fun `getProductDetails should return success when product exists`() = runTest {
        coEvery { dataSource.loadProductById("1") } returns fakeProduct

        val result = repository.getProductDetails("1")

        assertTrue(result is RepositoryResult.Success)
        assertEquals("Produto Teste", (result as RepositoryResult.Success).data.title)
    }

    @Test
    fun `getProductDetails should return failure when product is null`() = runTest {
        coEvery { dataSource.loadProductById("1") } returns null

        val result = repository.getProductDetails("1")

        assertTrue(result is RepositoryResult.Failure)
        assertEquals("Produto não encontrado", (result as RepositoryResult.Failure).error.message)
    }

    @Test
    fun `getProductDetails should return failure when exception is thrown`() = runTest {
        coEvery { dataSource.loadProductById("1") } throws RuntimeException("Falha inesperada")

        val result = repository.getProductDetails("1")

        assertTrue(result is RepositoryResult.Failure)
        assertEquals("Falha inesperada", (result as RepositoryResult.Failure).error.message)
    }
}
