package br.com.las.features.screens.products.list

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
import br.com.las.core.domain.model.Product
import br.com.las.ui.components.ProductListItemCard
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductListContent(
    products: List<Product>,
    listState: LazyListState,
    isLoading: Boolean,
    onProductClick: (Product) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(15.dp),
        contentPadding = PaddingValues(vertical = 15.dp),
        state = listState
    ) {
        itemsIndexed(products) { _, product ->
            ProductListItemCard(
                imagePainter = rememberAsyncImagePainter(model = product.thumbnailUrl),
                title = product.title,
                description = "teste",
                extraInfo = "teste",
                location = "teste",
                modifier = Modifier
                    .clickable { onProductClick(product) }
            )
        }

        if (isLoading) {
//            item {
//                Box(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(16.dp),
//                    contentAlignment = Alignment.Center
//                ) {
//                    CircularProgressIndicator()
//                }
//            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListContentPreview() {
    val sampleProducts = listOf(
        Product(
            id = "1",
            title = "Title test",
            city = "City Test",
            storeName = "Store Name Test",
            thumbnailUrl = "https://via.placeholder.com/150",
            price = 10.0,
            state = "State test"
        ),

        Product(
            id = "2",
            title = "Title test",
            city = "City Test",
            storeName = "Store Name Test",
            thumbnailUrl = "https://via.placeholder.com/150",
            price = 10.0,
            state = "State test"
        ),

        Product(
            id = "3",
            title = "Title test",
            city = "City Test",
            storeName = "Store Name Test",
            thumbnailUrl = "https://via.placeholder.com/150",
            price = 10.0,
            state = "State test"
        )
    )

    ProductListContent(
        products = sampleProducts,
        listState = rememberLazyListState(),
        isLoading = false,
        onProductClick = {}
    )
}
