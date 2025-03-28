package br.com.las.data.datasource.remote.api

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface GetItemsApiService {

    @GET("sites/MLB/search")
    suspend fun searchItems(
        @Header("Authorization") authorization: String,
        @Query("q") query: String,
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 10
    ): List<Any>

}