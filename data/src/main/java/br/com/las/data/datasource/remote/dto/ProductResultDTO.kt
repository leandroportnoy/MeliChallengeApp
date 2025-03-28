package br.com.las.data.datasource.remote.dto

import com.google.gson.annotations.SerializedName

data class ItemDto(
    val id: String,
    val title: String,
    @SerializedName("thumbnail_id") val thumbnailId: String?,
    @SerializedName("catalog_product_id") val catalogProductId: String?,
    val permalink: String?,
    @SerializedName("domain_id") val domainId: String?,
    val thumbnail: String?,
    @SerializedName("currency_id") val currencyId: String?,
    @SerializedName("order_backend") val orderBackend: Int?,
    val price: Double?,
    @SerializedName("available_quantity") val availableQuantity: Int?,
    @SerializedName("official_store_name") val officialStoreName: String?,
    @SerializedName("use_thumbnail_id") val useThumbnailId: Boolean?,
    @SerializedName("accepts_mercadopago") val acceptsMercadoPago: Boolean?,
    val seller: SellerDto?,
    val address: AddressDto?,
    val attributes: List<AttributeDto>?,
    val installments: InstallmentsDto?,
    @SerializedName("result_type") val resultType: String?
)

data class SellerDto(
    val id: Long,
    val nickname: String?
)

data class AddressDto(
    @SerializedName("state_id") val stateId: String?,
    @SerializedName("state_name") val stateName: String?,
    @SerializedName("city_id") val cityId: String?,
    @SerializedName("city_name") val cityName: String?
)

data class AttributeDto(
    val id: String?,
    val name: String?,
    @SerializedName("value_id") val valueId: String?,
    @SerializedName("value_name") val valueName: String?,
    @SerializedName("attribute_group_id") val attributeGroupId: String?,
    @SerializedName("attribute_group_name") val attributeGroupName: String?,
    @SerializedName("value_struct") val valueStruct: ValueStructDto?,
    val values: List<ValueDto>?,
    val source: Int?,
    @SerializedName("value_type") val valueType: String?
)

data class ValueStructDto(
    val number: Double?,
    val unit: String?
)

data class ValueDto(
    val id: String?,
    val name: String?,
    val struct: ValueStructDto?,
    val source: Int?
)

data class InstallmentsDto(
    val quantity: Int?,
    val amount: Double?,
    val rate: Double?,
    @SerializedName("currency_id") val currencyId: String?,
    val metadata: MetadataDto?
)

data class MetadataDto(
    @SerializedName("meliplus_installments") val meliplusInstallments: Boolean?,
    @SerializedName("additional_bank_interest") val additionalBankInterest: Boolean?
)

