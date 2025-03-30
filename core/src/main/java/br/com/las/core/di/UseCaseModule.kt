package br.com.las.core.di

import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.usecase.GetProductDetailsUseCase
import br.com.las.core.domain.usecase.GetProductDetailsUseCaseImpl
import br.com.las.core.domain.usecase.SearchProductsUseCase
import br.com.las.core.domain.usecase.SearchProductsUseCaseImpl
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
    ): SearchProductsUseCase {
        return SearchProductsUseCaseImpl(repository)
    }

    @Provides
    @Singleton
    fun provideGetProductDetailsUseCase(
        repository: ProductsRepository
    ): GetProductDetailsUseCase {
        return GetProductDetailsUseCaseImpl(repository)
    }
}