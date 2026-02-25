package com.google.uala_challenge.features.details.presenter.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.uala_challenge.core.constants.ERROR_EMPTY_RESPONSE
import com.google.uala_challenge.features.details.domain.model.CityDetailModel
import com.google.uala_challenge.features.details.presenter.bloc.DetailEvent
import com.google.uala_challenge.features.details.presenter.viewmodel.DetailViewModel
import com.google.uala_challenge.ui.theme.Black
import com.google.uala_challenge.ui.theme.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    cityId: Int,
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(cityId) {
        viewModel.sendEvent(DetailEvent.GetDetail(cityId))
    }

    Column(modifier = modifier.fillMaxSize()) {
        TopAppBar(
            title = { Text("Detail") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        )
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
            state.error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = ERROR_EMPTY_RESPONSE,
                        style = MaterialTheme.typography.bodyLarge,
                        color = Gray
                    )
                }
            }
            else -> state.detail?.let { detail ->
                DetailContent(
                    detail = detail,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
private fun DetailContent(
    detail: CityDetailModel,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(20.dp)
    ) {
        Text(
            text = detail.name,
            style = MaterialTheme.typography.headlineMedium,
            color = Black,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = detail.country,
            style = MaterialTheme.typography.titleMedium,
            color = Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Coordinate",
            style = MaterialTheme.typography.labelLarge,
            color = Black,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = "${detail.coordinates.latitude}, ${detail.coordinates.longitude}",
            style = MaterialTheme.typography.bodyMedium,
            color = Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Población",
            style = MaterialTheme.typography.labelLarge,
            color = Black,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = detail.population,
            style = MaterialTheme.typography.bodyMedium,
            color = Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Hours",
            style = MaterialTheme.typography.labelLarge,
            color = Black,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = detail.timezone,
            style = MaterialTheme.typography.bodyMedium,
            color = Gray
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Description",
            style = MaterialTheme.typography.labelLarge,
            color = Black,
            fontWeight = FontWeight.SemiBold
        )
        Text(
            text = detail.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Gray,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
