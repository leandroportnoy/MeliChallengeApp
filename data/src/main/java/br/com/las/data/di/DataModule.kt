package br.com.las.data.di

import br.com.las.core.domain.repository.ProductsRepository
import br.com.las.data.datasource.remote.api.GetItemsApiService
import br.com.las.data.repository.ProductsRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    private const val BASE_URL = "https://api.mercadolibre.com/"

    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideGetItemsApiService(retrofit: Retrofit): GetItemsApiService {
        return retrofit.create(GetItemsApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideProductsRepository(
        apiService: GetItemsApiService
    ): ProductsRepository {
        return ProductsRepositoryImpl(apiService)
    }
}
