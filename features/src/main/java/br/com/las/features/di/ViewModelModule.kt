package br.com.las.features.di

import androidx.lifecycle.SavedStateHandle
import br.com.las.core.domain.usecase.ListProductsUseCase
import br.com.las.core.domain.usecase.ProductDetailsUseCase
import br.com.las.features.screens.products.details.ProductDetailsViewModel
import br.com.las.features.screens.products.list.ProductListViewModel
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    @ViewModelScoped
    fun provideProductListViewModel(
        listProductsUseCase: ListProductsUseCase,
    ): ProductListViewModel {
        return ProductListViewModel(
            listProductsUseCase = listProductsUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun provideProductDetailsViewModel(
        productDetailsUseCase: ProductDetailsUseCase
    ): ProductDetailsViewModel {
        return ProductDetailsViewModel(
            productDetailsUseCase = productDetailsUseCase
        )
    }

}