package br.com.las.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.las.ui.MeliBackground
import br.com.las.ui.R


@Composable
fun Logo(
    modifier: Modifier = Modifier,
    image: Int = R.drawable.meli_logo,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MeliBackground)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = null,
            modifier = Modifier
                .size(320.dp)
                .background(MeliBackground)

        )
    }
}

@Preview(showBackground = true)
@Composable
fun LogoPreview() {
    Logo()
}
