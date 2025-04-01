package br.com.las.ui.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.ui.MeliBackground
import br.com.las.ui.MeliChallengeTheme
import br.com.las.ui.MeliSurface
import coil.compose.rememberAsyncImagePainter

@Composable
fun ProductListItemCard(
    imagePainter: Painter,
    title: String,
    description: String,
    extraInfo: String,
    location: String,
    modifier: Modifier = Modifier
) {
    val colors = MaterialTheme.colorScheme

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp),
        tonalElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MeliSurface, shape = RoundedCornerShape(14.dp))
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = imagePainter,
                contentDescription = "Product Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(8.dp))
            )

            Column (
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    color = colors.onSurface,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurface,
                    maxLines = 2
                )

                Text(
                    text = extraInfo,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurface.copy(alpha = 0.6f),
                    maxLines = 2
                )

                Text(
                    text = location,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.onSurface,
                    maxLines = 2
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductListItemCardPreview() {
    Column(modifier = Modifier
        .background(MeliBackground)) {
        MeliChallengeTheme {
            ProductListItemCard(
                imagePainter = ColorPainter(Color.Gray),
                title = "Produto Exemplo",
                description = "Descrição do produto",
                extraInfo = "Informação extra",
                location = "Localização"
            )

            ProductListItemCard(
                imagePainter = ColorPainter(Color.Gray),
                title = "Produto Exemplo",
                description = "Descrição do produto",
                extraInfo = "Informação extra",
                location = "Localização"
            )
        }
    }
}
