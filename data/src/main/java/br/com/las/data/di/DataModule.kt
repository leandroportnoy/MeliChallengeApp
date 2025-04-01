package br.com.las.data.di

import android.content.Context
import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.data.datasource.assets.LocalJsonProductDataSource
import br.com.las.data.repository.ProductsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Provides
    fun provideLocalJsonDataSource(@ApplicationContext context: Context): LocalJsonProductDataSource {
        return LocalJsonProductDataSource(context)
    }

    @Provides
    fun provideProductsRepository(
        dataSource: LocalJsonProductDataSource
    ): ProductsRepository {
        return ProductsRepositoryImpl(dataSource)
    }
}
