package br.com.las.core.di

import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.core.domain.usecase.GetProductDetailsUseCase
import br.com.las.core.domain.usecase.GetProductDetailsUseCaseImpl
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
    ): GetProductDetailsUseCase {
        return GetProductDetailsUseCaseImpl(repository)
    }
}