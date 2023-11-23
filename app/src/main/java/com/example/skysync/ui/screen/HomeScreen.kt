package com.example.skysync.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.skysync.LocationDetails
import com.example.skysync.data.room.EntityItem
import com.example.skysync.ui.components.CityWeatherItem
import com.example.skysync.ui.components.CoilImage
import com.example.skysync.ui.components.WeatherDetailsTab
import com.example.skysync.ui.theme.SkySyncTheme
import java.text.DecimalFormat

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel(),
    currentLocation: LocationDetails
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()
    if (homeUiState.itemList.size > 0)
        homeUiState.data?.let { HomeUI(data = it, citiesList = homeUiState.itemList) }

}

@Composable
fun HomeUI(data: EntityItem, citiesList: List<EntityItem>) {
    Column(modifier = Modifier) {
        TopBanner(data = data)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "city", modifier = Modifier
                    .weight(0.3f)
                    .padding(12.dp), textAlign = TextAlign.Justify
            )
            Spacer(modifier = Modifier.weight(0.3f))
            Text(text = "temp", modifier = Modifier.weight(0.2f), textAlign = TextAlign.Justify)
            Text(text = "min/max", modifier = Modifier.weight(0.3f), textAlign = TextAlign.Justify)
        }
        LazyColumn {
            items(citiesList) { city ->
                CityCard(data = city)
            }
            item {
                Spacer(modifier = Modifier.height(30.dp))
            }
        }
    }

}

@Composable
fun TopBanner(data: EntityItem) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(vertical = commonPadding),
        elevation = CardDefaults.cardElevation(20.dp),
        shape = RoundedCornerShape(bottomEnd = 30.dp, bottomStart = 30.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.statusBars),
            verticalArrangement = Arrangement.spacedBy(commonPadding, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${data.name}, ${data.country}",
                modifier = Modifier.padding(bottom = 15.dp),
                style = MaterialTheme.typography.headlineSmall
            )
            CoilImage(
                iconcode = data.iconCode.toString(),
                modifier = Modifier.scale(3f),
                contentScale = ContentScale.FillBounds
            )
            val df = DecimalFormat("00.00")
            val temp = df.format(data.temp)
            Text(
                text = "$temp℃",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.ExtraBold)
            )
            Text(text = "Feel like ${data.feelsLike}℃")
            Text(
                text = data.description,
                style = MaterialTheme.typography.labelLarge
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(commonPadding),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                WeatherDetailsTab(lable = "${data.windSpeed}km/hr", icon = Icons.Filled.Air)
                WeatherDetailsTab(lable = "${data.rain}%", icon = Icons.Filled.WaterDrop)
                WeatherDetailsTab(lable = "${data.cloud}%", icon = Icons.Filled.Cloud)
            }
        }
    }
}

@Composable
fun CityCard(data: EntityItem) {

    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        CityWeatherItem(data = data)
    }

}


private val commonPadding = 8.dp

@Preview
@Composable
fun PreviewHomeScreen() {
    SkySyncTheme {
        HomeScreen(currentLocation = LocationDetails(44.34, 10.99))
    }
}

