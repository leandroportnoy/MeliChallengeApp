package br.com.las.features.navigation

import android.window.SplashScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.las.features.screens.splash.SplashScreen

@Composable
fun AppNavigation(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Splash.route
    ) {
        composable(NavigationRoutes.Splash.route) {
            SplashScreen {
//                navController.navigate(NavigationRoutes.ProductList.route) {
//                    popUpTo(NavigationRoutes.Splash.route) { inclusive = true }
//                }
            }
        }
//
//        composable(NavigationRoutes.ProductList.route) {
//            ProductListScreen(onProductClick = { product ->
//                navController.navigate(NavigationRoutes.ProductDetails.createRoute(product.id))
//            })
//        }
//
//        composable(
//            route = NavigationRoutes.ProductDetails.route,
//            arguments = listOf(navArgument("productId") { type = NavType.StringType })
//        ) { backStackEntry ->
//            val productId = backStackEntry.arguments?.getString("productId") ?: ""
//            ProductDetailsScreen(productId = productId)
//        }
    }
}
