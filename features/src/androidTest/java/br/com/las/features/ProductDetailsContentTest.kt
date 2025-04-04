package br.com.las.features

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import br.com.las.core.domain.model.Product
import br.com.las.core.domain.model.ProductAttribute
import br.com.las.features.screens.products.details.ProductDetailsContent
import org.junit.Rule
import org.junit.Test

class ProductDetailsContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val fakeProduct = Product(
        id = "123",
        title = "Produto Teste",
        description = "Descrição do Produto",
        price = 149.99,
        pictures = listOf("https://example.com/image.jpg"),
        currency = "1000",
        thumbnailUrl = "https://example.com/image.jpg",
        attributes = listOf(
            ProductAttribute(
                name = "Atributo teste",
                value = "Atributo valor",
            )
        )

    )

    @Test
    fun productDetailsContent_displaysProductInfo() {
        composeTestRule.setContent {
            ProductDetailsContent(
                product = fakeProduct,
                onBack = {}
            )
        }

        composeTestRule.onNodeWithText("Produto Teste").assertIsDisplayed()
        composeTestRule.onNodeWithText("Descrição do Produto").assertIsDisplayed()
        composeTestRule.onNodeWithText("R$ 149,99").assertIsDisplayed()
        composeTestRule.onNodeWithText("São Paulo").assertIsDisplayed()
        composeTestRule.onNodeWithText("Voltar").assertIsDisplayed()
    }

    @Test
    fun productDetailsContent_backButtonTriggersCallback() {
        var backCalled = false

        composeTestRule.setContent {
            ProductDetailsContent(
                product = fakeProduct,
                onBack = { backCalled = true }
            )
        }

        composeTestRule.onNodeWithText("Voltar").performClick()
        assert(backCalled)
    }
}
