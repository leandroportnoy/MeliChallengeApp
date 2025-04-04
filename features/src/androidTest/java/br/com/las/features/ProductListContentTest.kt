package br.com.las.features

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import br.com.las.core.domain.model.Product
import br.com.las.features.screens.products.list.ProductListContent
import org.junit.Rule
import org.junit.Test

class ProductListContentTest {

    @get:Rule
    val composeRule = createComposeRule()

    private val products = listOf(
        Product(
            id = "1",
            title = "Produto 1",
            price = 99.9,
            currency = "BRL",
            thumbnailUrl = "https://via.placeholder.com/150",
            pictures = listOf(),
            description = "Descrição do Produto 1",
            attributes = emptyList()
        ),
        Product(
            id = "2",
            title = "Produto 2",
            price = 199.9,
            currency = "BRL",
            thumbnailUrl = "https://via.placeholder.com/150",
            pictures = listOf(),
            description = "Descrição do Produto 2",
            attributes = emptyList()
        )
    )

    @Test
    fun productListContent_displaysProductList() {
        composeRule.setContent {
            ProductListContent(
                products = products,
                listState = androidx.compose.foundation.lazy.rememberLazyListState(),
                onProductClick = {}
            )
        }

        composeRule.onNodeWithText("Produto 1").assertIsDisplayed()
        composeRule.onNodeWithText("Descrição do Produto 1").assertIsDisplayed()
        composeRule.onNodeWithText("Produto 2").assertIsDisplayed()
        composeRule.onNodeWithText("Descrição do Produto 2").assertIsDisplayed()
    }

    @Test
    fun productListContent_onProductClick_called() {
        var clickedProduct: Product? = null

        composeRule.setContent {
            ProductListContent(
                products = products,
                listState = androidx.compose.foundation.lazy.rememberLazyListState(),
                onProductClick = { clickedProduct = it }
            )
        }

        composeRule.onNodeWithText("Produto 1").performClick()

        assert(clickedProduct?.id == "1")
    }
}
