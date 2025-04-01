package br.com.las.features.navigation

import android.util.Log
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.las.features.screens.products.details.ProductDetailsScreen
import br.com.las.features.screens.products.list.ProductListScreen
import br.com.las.features.screens.splash.SplashScreen

@Composable
fun AppNavigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Splash.route
    ) {

        composable(NavigationRoutes.Splash.route) {
            SplashScreen {
                navController.navigate(NavigationRoutes.ProductList.route) {
                    popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
                }
            }
        }

        composable(NavigationRoutes.ProductList.route) {
            ProductListScreen(
                listState = rememberLazyListState(),
                onNavigateToProductDetail = { productId ->
                    navController.navigate(NavigationRoutes.ProductDetails.createRoute(productId))
                }
            )
        }

        composable(
            route = NavigationRoutes.ProductDetails.route,
            arguments = listOf(
                navArgument("productId") { type = NavType.StringType }
            )
        ) {
            val productId = it.arguments?.getString("productId") ?: ""
            ProductDetailsScreen(
                productId = productId,
                onBack = {
                    Log.d("NAV", "Pop back triggered")
                    navController.popBackStack()
                }
            )
        }
    }
}
