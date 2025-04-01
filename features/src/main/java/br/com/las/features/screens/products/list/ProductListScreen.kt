package br.com.las.features.screens.products.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
) {
    val viewModel: ProductListViewModel = hiltViewModel()

    val productState = viewModel.productListState.collectAsState()
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
                .padding(horizontal = 20.dp, vertical = 10.dp)
        ) {
            when {
                searchQuery.value.isNotEmpty() -> {
                    when (searchResultState.value) {
                        is UiState.Loading -> {
                            CircularProgressIndicator(
                                modifier = androidx.compose.ui.Modifier.align(
                                    Alignment.Center
                                )
                            )
                        }

                        is UiState.Success -> {
                            ProductListContent(
                                products = (searchResultState as UiState.Success<List<Product>>).data,
                                listState = listState,
                                isLoading = productState.value is UiState.Loading,
                                onProductClick = { product ->
                                    //TODO Handle product click
                                },
                            )
                        }

                        is UiState.Empty -> {
                            EmptySearchResult()
                        }

                        is UiState.Failure -> {
                            //TODO : Show error message
                        }

                        else -> Unit
                    }
                }
                else -> {
                    when (productState.value) {
                        is UiState.Loading -> {
                            // Show loading indicator
                        }

                        is UiState.Success -> {
                            ProductListContent(
                                products = (productState as UiState.Success<List<Product>>).data,
                                listState = listState,
                                isLoading = productState.value is UiState.Loading,
                                onProductClick = { product ->
                                    // Handle product click
                                },
                            )
                        }

                        is UiState.Empty -> {
                            EmptySearchResult()
                        }

                        is UiState.Failure -> {
                            // Show error message
                        }

                        is UiState.Idle -> {
                            // Show idle state
                        }
                    }
                }
            }
        }

    }
}


@Composable
private fun EmptySearchResult() {
    //TODO: Implement empty search result UI
}

@Composable
private fun IdleState() {
    Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Message" /*stringResource(R.string.login_welcome)*/,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListScreenPreview() {
    ProductListScreen(
        listState = LazyListState()
    )
}
