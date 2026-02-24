package com.google.uala_challenge.presenter.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.uala_challenge.presenter.bloc.CitiesEvent
import com.google.uala_challenge.presenter.composables.BottomSheetMap
import com.google.uala_challenge.presenter.composables.ItemCity
import com.google.uala_challenge.presenter.composables.SearchComponent
import com.google.uala_challenge.presenter.composables.SkeletonCityItem
import com.google.uala_challenge.presenter.viewmodel.CitiesViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CitiesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: CitiesViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    LaunchedEffect(Unit) {
        viewModel.sendEvent(CitiesEvent.GetCities)
    }

    state.selectedCity?.let { city ->
        ModalBottomSheet(
            onDismissRequest = { viewModel.sendEvent(CitiesEvent.SelectCity(null)) },
            sheetState = bottomSheetState
        ) {
            BottomSheetMap(city = city)
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        SearchComponent(
            onQueryChange = { query ->
                viewModel.sendEvent(CitiesEvent.SearchCity(query, state.data))
            }
        )

        when {
            state.isLoading -> {
                LazyColumn {
                    items(
                        count = 10,
                        key = { it }
                    ) {
                        SkeletonCityItem()
                    }
                }
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
                    LazyColumn {
                        items(
                            items = it,
                            key = { citi -> citi.id }
                        ) { citi ->
                            ItemCity(
                                city = citi,
                                onClick = { viewModel.sendEvent(CitiesEvent.SelectCity(citi)) }
                            )
                        }
                    }
                }
            }
        }
    }
}
