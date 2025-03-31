package br.com.las.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorPalette = lightColorScheme(
    primary = MeliYellow,
    secondary = MeliBlue,
    background = MeliBackground,
    surface = MeliSurface,
    onPrimary = MeliTextPrimary,
    onSecondary = MeliBackground,
    onBackground = MeliTextPrimary,
    onSurface = MeliTextPrimary
)

@Composable
fun MeliChallengeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorPalette,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}