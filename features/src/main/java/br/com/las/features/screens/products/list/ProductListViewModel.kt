package br.com.las.features.screens.products.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.core.domain.model.Product
import br.com.las.core.domain.model.UiState
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.core.domain.usecase.ListProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductListViewModel @Inject constructor (
    private val listProductsUseCase: ListProductsUseCase
) : ViewModel() {

    private val _productListState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val productListState: StateFlow<UiState<List<Product>>> = _productListState.asStateFlow()

    private val _searchResultState = MutableStateFlow<UiState<List<Product>>>(UiState.Idle)
    val searchResultState: StateFlow<UiState<List<Product>>> = _searchResultState.asStateFlow()

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    init {
        initialize()
    }

    private fun initialize() {
        fetchProducts()
    }

    private fun searchProducts(query: String) {
        viewModelScope.launch {
            _searchResultState.value = UiState.Loading

            when (val result = listProductsUseCase.invoke(query)) {
                is RepositoryResult.Success -> {
                    if (result.data.isEmpty()) {
                        _searchResultState.value = UiState.Empty
                    } else {
                        _searchResultState.value = UiState.Success(result.data)
                    }
                }

                is RepositoryResult.Failure -> {
                    _searchResultState.value = UiState.Failure(
                        errorCode = result.error.code,
                        errorMessage = result.error.message
                    )
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        if (query.isNotEmpty()) {
            searchProducts(query)
        } else {
            _searchResultState.value = UiState.Idle
        }
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            _productListState.value = UiState.Loading

            when (val result = listProductsUseCase.invoke("")) {
                is RepositoryResult.Success -> {
                    if (result.data.isEmpty()) {
                        _productListState.value = UiState.Empty
                    } else {
                        _productListState.value = UiState.Success(result.data)
                    }
                }

                is RepositoryResult.Failure -> {
                    _productListState.value = UiState.Failure(
                        errorCode = result.error.code,
                        errorMessage = result.error.message
                    )
                }
            }
        }
    }
}