package com.example.skysync.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.skysync.R
import com.example.skysync.data.room.EntityItem
import java.text.DecimalFormat

@Composable
fun CityWeatherItem(data: EntityItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = data.name, modifier = Modifier.weight(0.3f))
        Box(modifier = Modifier.weight(0.2f)) {
            CoilImage(
                iconcode = data.iconCode,
                contentScale = ContentScale.FillBounds
            )
        }
        Text(text = "${data.temp}℃", modifier = Modifier.weight(0.2f), textAlign = TextAlign.Center)
        val df = DecimalFormat("00.00")
        val min = df.format(data.minTemp)
        val max = df.format(data.maxTemp)
        Text(
            text = "$min / $max",
            modifier = Modifier.weight(0.3f)
        )

    }
}

@Composable
fun WeatherDetailsTab(lable: String, icon: ImageVector) {
    Row(
        modifier = Modifier.wrapContentSize(),
        horizontalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = lable + "icon")
        Text(text = lable)
    }
}


@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.loading_img),
            contentDescription = "laoding"
        )

    }
}

/**
 * The home screen displaying error message with re-attempt button.
 */
@Composable
fun ErrorScreen(message: String, retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Failed")
        Button(onClick = retryAction) {
            Text("retry")
        }

    }
}
