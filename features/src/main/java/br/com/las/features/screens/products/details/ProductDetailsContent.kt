package br.com.las.features.screens.products.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.las.common.toBrazilianCurrency
import br.com.las.core.domain.model.Product
import br.com.las.ui.MeliBackground
import br.com.las.ui.MeliSurface
import br.com.las.ui.components.BackButton
import br.com.las.ui.components.FavoriteButton
import br.com.las.ui.components.ShimmerImage
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductDetailsContent(
    product: Product,
    isLoading: Boolean,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MeliBackground)
            .padding(4.dp)
    ) {
        ShimmerImage(
            imageUrl = product.pictures.firstOrNull().orEmpty(),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .padding(24.dp)
        )

        Spacer(Modifier.height(8.dp))

        Surface(
            shape = RoundedCornerShape(16.dp),
            tonalElevation = 2.dp,
            color = MeliBackground,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(MeliSurface, shape = RoundedCornerShape(14.dp))
                    .padding(8.dp)
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 1
                )

                Text(
                    text = product.price.toBrazilianCurrency(),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp),
                    maxLines = 1
                )

                Text(
                    text = "São Paulo",
                    style = MaterialTheme.typography.bodySmall,
                    fontStyle = FontStyle.Italic,
                    modifier = Modifier.padding(top = 4.dp),
                    maxLines = 1
                )
            }
        }

        Spacer(Modifier.height(24.dp))

        // Botão de favoritar
        BackButton(
            onClick = {
                onBack()
            }
        )
    }
}
