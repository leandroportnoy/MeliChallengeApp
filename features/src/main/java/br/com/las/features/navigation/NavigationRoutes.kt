package br.com.las.features.navigation

sealed class NavigationRoutes(val route: String) {
    data object Splash : NavigationRoutes("splash")
    data object ProductList : NavigationRoutes("product_list")
    data object ProductDetails : NavigationRoutes("product_details/{productId}") {
        fun createRoute(productId: String) = "product_details/$productId"
    }
}