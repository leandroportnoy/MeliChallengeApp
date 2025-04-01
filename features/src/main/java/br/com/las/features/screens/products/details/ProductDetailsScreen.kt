package br.com.las.features.screens.products.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.las.core.domain.model.Product
import br.com.las.core.domain.model.UiState
import br.com.las.ui.MeliBackground

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailsScreen(
    productId: String,
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    LaunchedEffect(productId) {
        viewModel.loadProductDetails(productId)
    }

    val productState = viewModel.productState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalhes") },
                navigationIcon = {
                    IconButton(
                        onClick = onBack
                    ) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Voltar")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MeliBackground
                )
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .background(MeliBackground)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                when (productState.value) {
                    is UiState.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }

                    is UiState.Success -> {
                        val product = (productState.value as UiState.Success<Product>).data
                        ProductDetailsContent(
                            product = product,
                            isLoading = false,
                            onBack = onBack
                        )
                    }

                    is UiState.Failure -> {
                        val errorMessage = (productState.value as UiState.Failure).errorMessage
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(errorMessage ?: "Error loading product")
                        }
                    }
                    else -> Unit
                }
            }
        }
    )
}
