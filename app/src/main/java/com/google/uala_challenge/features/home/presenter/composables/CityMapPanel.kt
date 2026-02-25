package com.google.uala_challenge.features.home.presenter.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.uala_challenge.core.constants.DEFAULT_ZOOM
import com.google.uala_challenge.core.constants.MAP_LOAD_DELAY_MS
import com.google.uala_challenge.features.home.domain.model.CityModel
import com.google.uala_challenge.ui.theme.Gray
import kotlinx.coroutines.delay

@Composable
fun CityMapPanel(
    city: CityModel?,
    modifier: Modifier = Modifier
) {
    val defaultLatLng = remember { LatLng(0.0, 0.0) }
    val latLng = if (city != null) {
        remember(city.id) { LatLng(city.coordinates.latitude, city.coordinates.longitude) }
    } else {
        defaultLatLng
    }
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(latLng, if (city != null) DEFAULT_ZOOM else 2f)
    }
    var mapReady by remember { mutableStateOf(false) }

    LaunchedEffect(city?.id) {
        delay(MAP_LOAD_DELAY_MS)
        mapReady = true
        if (city != null) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(latLng, DEFAULT_ZOOM)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        if (mapReady) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = remember { MapProperties() },
                uiSettings = remember { MapUiSettings() }
            ) {
                if (city != null) {
                    Marker(
                        state = MarkerState(position = latLng),
                        title = city.name,
                        snippet = city.country
                    )
                }
            }
        }
        if (city == null) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Select city",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Gray
                )
            }
        }
    }
}