package br.com.las.data.datasource.remote.api

import br.com.las.data.datasource.remote.dto.ItemDto
import retrofit2.http.GET
import retrofit2.http.Query

interface GetItemsApiService {

    @GET("sites/MLB/search")
    suspend fun searchItems(
        @Query("q") query: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10
    ): List<ItemDto>

    @GET("sites/MLB/search")
    suspend fun getProductDetails(
        @Query("q") query: Long,
    ): ItemDto

}