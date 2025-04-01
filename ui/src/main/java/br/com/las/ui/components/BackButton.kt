package br.com.las.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.las.ui.MeliBlue
import br.com.las.ui.MeliChallengeTheme
import br.com.las.ui.MeliWhite


@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = MeliBlue,
            contentColor = MeliWhite
        )
    ) {
        Text(text = "Voltar", fontSize = 16.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun BackButtonPreview() {
    MeliChallengeTheme {
        BackButton(onClick = {})
    }
}


