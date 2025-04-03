package br.com.las.ui

import androidx.compose.ui.test.junit4.createComposeRule
import br.com.las.ui.components.ShimmerImage
import org.junit.Rule
import org.junit.Test

class ShimmerImageTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun shimmerImage_rendersWithoutCrashing() {
        composeRule.setContent {
            ShimmerImage(imageUrl = "https://example.com/test.png")
        }

    }
}
