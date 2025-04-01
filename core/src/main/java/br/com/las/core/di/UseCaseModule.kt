package br.com.las.core.di

import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.usecase.ProductDetailsUseCase
import br.com.las.core.domain.usecase.ProductDetailsUseCaseImpl
import br.com.las.core.domain.usecase.ListProductsUseCase
import br.com.las.core.domain.usecase.ListProductsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSearchProductsUseCase(
        repository: ProductsRepository
    ): ListProductsUseCase {
        return ListProductsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetProductDetailsUseCase(
        repository: ProductsRepository
    ): ProductDetailsUseCase {
        return ProductDetailsUseCaseImpl(repository)
    }
}