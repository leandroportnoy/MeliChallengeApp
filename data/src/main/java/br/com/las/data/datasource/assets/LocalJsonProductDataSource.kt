package br.com.las.data.datasource.assets

import android.content.Context
import br.com.las.core.domain.model.Product
import br.com.las.core.domain.model.ProductAttribute
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class LocalJsonProductDataSource(private val context: Context) {

    private val productIds = listOf(
        "MLA1116621831",
        "MLA1998544776",
        "MLA2005705454",
        "MLA1385224303",
        "MLA738241667",
        "MLA904895322",
    )

    suspend fun loadProducts(): List<Product> = withContext(Dispatchers.IO) {
        productIds.mapNotNull { id ->
            try {
                val itemJson = loadJson("item-$id.json")
                val descJson = loadJson("item-$id-description.json")
                val item = JSONObject(itemJson)
                val desc = JSONObject(descJson)

                val pictures = item.getJSONArray("pictures")
                val attributes = item.getJSONArray("attributes")

                Product(
                    id = item.getString("id"),
                    title = item.getString("title"),
                    price = item.getDouble("price"),
                    currency = item.optString("currency_id", "BRL"),
                    thumbnailUrl = item.optString("thumbnail", ""),
                    pictures = (0 until pictures.length()).map { i ->
                        pictures.getJSONObject(i).getString("url")
                    },
                    description = desc.optString("plain_text", ""),
                    attributes = (0 until attributes.length()).map { i ->
                        val attr = attributes.getJSONObject(i)
                        ProductAttribute(
                            name = attr.getString("name"),
                            value = attr.optString("value_name", "")
                        )
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

    private fun loadJson(fileName: String): String =
        context.assets.open(fileName).bufferedReader().use { it.readText() }
}