package br.com.las.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class SearchResultDto(
    @SerializedName("results")
    val results: List<ItemDto>?
)