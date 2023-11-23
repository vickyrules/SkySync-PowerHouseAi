package com.example.skysync.ui.components

import android.util.Half.toFloat
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun CoilImage(
    iconcode: String,
    modifier: Modifier = Modifier,
    contentScale: ContentScale
) {

    val data = "https://openweathermap.org/img/wn/$iconcode@4x.png"
    AsyncImage(
        model = ImageRequest
            .Builder(context = LocalContext.current)
            .data(data)
            .crossfade(true)
            .build(),
        modifier = modifier,
        contentScale = contentScale,
        contentDescription = "$data image",
    )


}