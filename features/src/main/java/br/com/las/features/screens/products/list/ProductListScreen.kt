package br.com.las.features.screens.products.list

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import br.com.las.core.domain.model.Product
import br.com.las.core.domain.model.UiState
import br.com.las.ui.MeliBackground
import br.com.las.ui.components.SearchBar

@Composable
fun ProductListScreen(
    listState: LazyListState,
    onNavigateToProductDetail: (String) -> Unit
) {
    val context = LocalContext.current
    val viewModel: ProductListViewModel = hiltViewModel()

    val productListState = viewModel.productListState.collectAsState()
    val searchResultState = viewModel.searchResultState.collectAsState()
    val searchQuery = viewModel.searchQuery.collectAsState()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(MeliBackground)
                    .statusBarsPadding()
            ) {
                SearchBar(
                    searchQuery = searchQuery.value,
                    onSearchQueryChange = viewModel::onSearchQueryChanged
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .background(MeliBackground)
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp, vertical = 8.dp)
        ) {
            when {
                searchQuery.value.isNotEmpty() -> {
                    when (searchResultState.value) {
                        is UiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }

                        is UiState.Success -> {
                            ProductListContent(
                                products = (searchResultState.value as UiState.Success<List<Product>>).data,
                                listState = listState,
                                onProductClick = { product ->
                                    onNavigateToProductDetail(product.id)
                                },
                            )
                        }

                        is UiState.Empty -> {
                            Toast.makeText(context, "⚠️ Lista vazia", Toast.LENGTH_LONG).show()
                        }

                        is UiState.Failure -> {
                            Toast.makeText(context, "⚠️ Erro ao carregar os dados", Toast.LENGTH_LONG).show()
                        }

                        else -> Unit
                    }
                }
                else -> {
                    when (productListState.value) {
                        is UiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }

                        is UiState.Success -> {
                            ProductListContent(
                                products = (productListState.value as UiState.Success<List<Product>>).data,
                                listState = listState,
                                onProductClick = { product ->
                                    onNavigateToProductDetail(product.id)
                                },
                            )
                        }

                        is UiState.Empty -> {
                            Toast.makeText(context, "⚠️ Lista vazia", Toast.LENGTH_LONG).show()
                        }

                        is UiState.Failure -> {
                            Toast.makeText(context, "⚠️ Erro ao carregar os dados", Toast.LENGTH_LONG).show()
                        }

                        else -> Unit
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    ProductListScreen(
        listState = LazyListState(),
        onNavigateToProductDetail = {}
    )
}
