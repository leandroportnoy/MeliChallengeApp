package br.com.las.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import br.com.las.ui.components.ProductListItemCard
import org.junit.Rule
import org.junit.Test

class ProductListItemCardTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun productListItemCard_displaysAllTexts() {
        composeRule.setContent {
            ProductListItemCard(
                imagePainter = ColorPainter(Color.Gray),
                title = "Produto Teste",
                description = "Descrição curta",
                extraInfo = "Estoque baixo",
                location = "São Paulo"
            )
        }

        composeRule.onNodeWithText("Produto Teste").assertIsDisplayed()
        composeRule.onNodeWithText("Descrição curta").assertIsDisplayed()
        composeRule.onNodeWithText("Estoque baixo").assertIsDisplayed()
        composeRule.onNodeWithText("São Paulo").assertIsDisplayed()
    }
}
