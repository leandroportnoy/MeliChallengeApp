package br.com.las.ui

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import br.com.las.ui.components.SearchBar
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun searchBar_displaysPlaceholderText() {
        composeRule.setContent {
            SearchBar(
                searchQuery = "",
                onSearchQueryChange = {}
            )
        }

        composeRule.onNodeWithText("Search products").assertIsDisplayed()
    }

    @Test
    fun searchBar_allowsTyping() {
        var query = ""

        composeRule.setContent {
            SearchBar(
                searchQuery = query,
                onSearchQueryChange = { query = it }
            )
        }

        val inputText = "Celular"

        composeRule
            .onNodeWithText("Search products")
            .performTextInput(inputText)

        assert(query == inputText)
    }
}
