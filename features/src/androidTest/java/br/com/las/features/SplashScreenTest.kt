package br.com.las.features

import androidx.compose.ui.test.junit4.createComposeRule
import br.com.las.features.navigation.NavigationRoutes
import br.com.las.features.screens.splash.SplashScreen
import junit.framework.TestCase.assertEquals
import org.junit.Rule
import org.junit.Test

class SplashScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun splashScreen_navigatesAfterDelay() {
        var navigatedRoute: NavigationRoutes? = null

        composeTestRule.setContent {
            SplashScreen(onNavigateToList = { route ->
                navigatedRoute = route
            })
        }

        composeTestRule.waitUntil(timeoutMillis = 3000) {
            navigatedRoute != null
        }

        assertEquals(NavigationRoutes.ProductList, navigatedRoute)
    }
}
