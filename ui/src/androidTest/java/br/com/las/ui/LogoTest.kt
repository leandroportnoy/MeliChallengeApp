package br.com.las.ui

import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.junit4.createComposeRule
import br.com.las.ui.components.Logo
import org.junit.Rule
import org.junit.Test

class LogoTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun logo_displaysImage() {
        composeTestRule.setContent {
            Logo(image = R.drawable.meli_logo)
        }

        composeTestRule
            .onNode(hasTestTag("logo-image"))
            .assertExists()
    }
}
