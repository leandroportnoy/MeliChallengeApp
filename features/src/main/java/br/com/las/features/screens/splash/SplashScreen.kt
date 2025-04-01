package br.com.las.features.screens.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import br.com.las.features.navigation.NavigationRoutes
import br.com.las.ui.MeliBackground
import br.com.las.ui.components.Logo
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToList: (NavigationRoutes) -> Unit
) {
    LaunchedEffect(Unit) {
        delay(2000)
        onNavigateToList(NavigationRoutes.ProductList)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MeliBackground),
        contentAlignment = Alignment.Center
    ) {
        Logo(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .aspectRatio(1f),
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(onNavigateToList = {})
}
