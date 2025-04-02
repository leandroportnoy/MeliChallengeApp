package br.com.las.features.screens.products.list

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.common.toBrazilianCurrency
import br.com.las.core.domain.model.Product
import br.com.las.ui.components.ProductListItemCard
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductListContent(
    products: List<Product>,
    listState: LazyListState,
    onProductClick: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = PaddingValues(vertical = 4.dp),
        state = listState
    ) {
        itemsIndexed(products) { _, product ->
            val imagePainter = rememberAsyncImagePainter(
                model = product.thumbnailUrl,
                onError = { Log.e("ImageLoad", "Erro: ${it.result.throwable}") }
            )
            ProductListItemCard(
                imagePainter = imagePainter,
                title = product.title,
                description = product.description,
                extraInfo = product.price.toBrazilianCurrency(),
                location = "---",
                modifier = Modifier
                    .clickable { onProductClick(product) }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListContentPreview() {
    val sampleProducts = listOf(
        Product(
            id = "1",
            title = "Product 1",
            price = 10.0,
            currency = "USD",
            thumbnailUrl = "https://via.placeholder.com/150",
            pictures = listOf("https://via.placeholder.com/150"),
            description = "Description of Product 1",
            attributes = emptyList()
        ),
        Product(
            id = "1",
            title = "Product 1",
            price = 10.0,
            currency = "USD",
            thumbnailUrl = "https://via.placeholder.com/150",
            pictures = listOf("https://via.placeholder.com/150"),
            description = "Description of Product 1",
            attributes = emptyList()
        ),
        Product(
            id = "1",
            title = "Product 1",
            price = 10.0,
            currency = "USD",
            thumbnailUrl = "https://via.placeholder.com/150",
            pictures = listOf("https://via.placeholder.com/150"),
            description = "Description of Product 1",
            attributes = emptyList()
        )

    )

    ProductListContent(
        products = sampleProducts,
        listState = rememberLazyListState(),
        onProductClick = {}
    )
}
