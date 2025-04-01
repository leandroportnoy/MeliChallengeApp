package br.com.las.features.screens.products.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.las.core.domain.model.Product
import br.com.las.core.domain.model.UiState
import br.com.las.core.domain.result.RepositoryResult
import br.com.las.core.domain.usecase.ProductDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productDetailsUseCase: ProductDetailsUseCase
): ViewModel() {

    private val _productState = MutableStateFlow<UiState<Product>>(UiState.Idle)
    val productState: StateFlow<UiState<Product>> = _productState.asStateFlow()

    fun loadProductDetails(productId: String) {
        viewModelScope.launch {
            _productState.value = UiState.Loading

            when (val result = productDetailsUseCase.invoke(productId)) {
                is RepositoryResult.Success -> {
                    _productState.value = UiState.Success(result.data)
                }

                is RepositoryResult.Failure -> {
                    _productState.value = UiState.Failure(
                        errorCode = result.error.code,
                        errorMessage = result.error.message
                    )
                }
            }
        }
    }
}