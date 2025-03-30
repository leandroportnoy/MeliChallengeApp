package br.com.las.data.datasource.remote.dto.mappers

import br.com.las.core.domain.model.Product
import br.com.las.data.datasource.remote.dto.ItemDto

fun ItemDto.toDomain(): Product {
    return Product(
        id = id,
        title = title,
        price = price ?: 0.0,
        thumbnailUrl = thumbnail.orEmpty(),
        storeName = officialStoreName,
        city = address?.cityName,
        state = address?.stateName
    )
}