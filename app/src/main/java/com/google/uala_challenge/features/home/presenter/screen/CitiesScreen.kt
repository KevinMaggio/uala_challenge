package com.google.uala_challenge.features.home.presenter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.uala_challenge.features.home.presenter.bloc.CitiesEvent
import com.google.uala_challenge.features.home.presenter.composables.BottomSheetMap
import com.google.uala_challenge.features.home.presenter.composables.CityMapPanel
import com.google.uala_challenge.features.home.presenter.composables.FavoritePill
import com.google.uala_challenge.features.home.presenter.composables.ItemCity
import com.google.uala_challenge.features.home.presenter.composables.SearchComponent
import com.google.uala_challenge.features.home.domain.model.CitiesState
import com.google.uala_challenge.features.home.presenter.viewmodel.CitiesViewModel
import android.content.res.Configuration

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(
    onNavigateToDetail: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val viewModel: CitiesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    val isLandscape = LocalConfiguration.current.orientation == Configuration.ORIENTATION_LANDSCAPE

    LaunchedEffect(Unit) {
        if (viewModel.state.value.data == null) {
            viewModel.sendEvent(CitiesEvent.GetCities)
        }
    }

    if (!isLandscape) {
        state.selectedCity?.let { city ->
            ModalBottomSheet(
                onDismissRequest = { viewModel.sendEvent(CitiesEvent.SelectCity(null)) },
                sheetState = bottomSheetState
            ) {
                BottomSheetMap(city = city)
            }
        }
    }

    when {
        isLandscape -> Row(modifier = modifier.fillMaxSize()) {
            ListPanel(
                state = state,
                viewModel = viewModel,
                onNavigateToDetail = onNavigateToDetail,
                modifier = Modifier.weight(1f)
            )
            CityMapPanel(
                city = state.selectedCity,
                modifier = Modifier.weight(1f)
            )
        }

        else -> ListPanel(
            state = state,
            viewModel = viewModel,
            onNavigateToDetail = onNavigateToDetail,
            modifier = modifier.fillMaxSize()
        )
    }
}

@Composable
private fun ListPanel(
    state: CitiesState,
    viewModel: CitiesViewModel,
    onNavigateToDetail: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        SearchComponent(
            onQueryChange = { query ->
                viewModel.sendEvent(CitiesEvent.SearchCity(query, state.data))
            }
        )
        ContentList(
            state = state,
            viewModel = viewModel,
            onNavigateToDetail = onNavigateToDetail
        )
    }
}

@Composable
private fun ContentList(
    state: CitiesState,
    viewModel: CitiesViewModel,
    onNavigateToDetail: (Int) -> Unit
) {
    when {
        state.isLoading -> {
            LoadingScreen()
        }

        state.errorInternet -> {
            ErrorConnectionScreen(
                onRetry = { viewModel.sendEvent(CitiesEvent.GetCities) }
            )
        }

        state.error -> {
            ErrorServerScreen(
                onRetry = { viewModel.sendEvent(CitiesEvent.GetCities) }
            )
        }

        else -> {
            state.filteredList?.let {
                FavoritePill(
                    isActive = state.showOnlyFavorites,
                    onClick = { viewModel.sendEvent(CitiesEvent.ToggleFilterFavorites) }
                )

                LazyColumn {
                    items(
                        items = it,
                        key = { citi -> citi.id }
                    ) { citi ->
                        ItemCity(
                            city = citi,
                            onFavoriteClick = {
                                viewModel.sendEvent(
                                    CitiesEvent.ToggleFavorite(citi.id)
                                )
                            },
                            onChevronClick = { onNavigateToDetail(citi.id) },
                            onClick = { viewModel.sendEvent(CitiesEvent.SelectCity(citi)) }
                        )
                    }
                }
            }
        }
    }
}
