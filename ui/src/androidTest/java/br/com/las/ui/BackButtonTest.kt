package br.com.las.ui

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import br.com.las.ui.components.BackButton
import org.junit.Rule
import org.junit.Test

class BackButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun backButton_showsCorrectText() {
        composeTestRule.setContent {
            BackButton(onClick = {})
        }

        composeTestRule
            .onNodeWithText("Voltar")
            .assertIsDisplayed()
    }

    @Test
    fun backButton_triggersOnClick() {
        var wasClicked = false

        composeTestRule.setContent {
            BackButton(onClick = { wasClicked = true })
        }

        composeTestRule
            .onNodeWithText("Voltar")
            .performClick()

        assert(wasClicked)
    }
}
