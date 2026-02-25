package com.google.uala_challenge.features.home.presenter.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.uala_challenge.core.utils.toTwoDecimalString
import com.google.uala_challenge.features.home.domain.model.CityModel
import com.google.uala_challenge.ui.theme.Black
import com.google.uala_challenge.ui.theme.Gray
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.uala_challenge.core.constants.DEFAULT_ZOOM
import com.google.uala_challenge.core.constants.MAP_HEIGHT_DP
import com.google.uala_challenge.core.constants.MAP_LOAD_DELAY_MS
import kotlinx.coroutines.delay

@Composable
fun BottomSheetMap(
    city: CityModel,
    modifier: Modifier = Modifier
) {
    val lat = city.coordinates.latitude
    val lon = city.coordinates.longitude
    val latLng = remember(lat, lon) { LatLng(lat, lon) }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, DEFAULT_ZOOM)
    }
    var mapReady by remember { mutableStateOf(false) }

    LaunchedEffect(city.id) {
        delay(MAP_LOAD_DELAY_MS)
        mapReady = true
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Text(
            text = city.name,
            style = MaterialTheme.typography.titleLarge,
            color = Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${city.country} • ${city.coordinates.latitude.toTwoDecimalString()}, ${city.coordinates.longitude.toTwoDecimalString()}",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray,
            modifier = Modifier.padding(top = 4.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(MAP_HEIGHT_DP.dp)
                .padding(top = 16.dp)
        ) {
            if (mapReady) {
                GoogleMap(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(MAP_HEIGHT_DP.dp),
                    cameraPositionState = cameraPositionState,
                    properties = remember { MapProperties() },
                    uiSettings = remember { MapUiSettings() }
                ) {
                    Marker(
                        state = MarkerState(position = latLng),
                        title = city.name,
                        snippet = city.country
                    )
                }
            }
        }
    }
}
