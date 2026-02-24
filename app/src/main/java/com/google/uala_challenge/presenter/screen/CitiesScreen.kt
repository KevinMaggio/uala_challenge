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
import com.google.uala_challenge.presenter.composables.ItemCity
import com.google.uala_challenge.presenter.composables.SearchComponent
import com.google.uala_challenge.presenter.viewmodel.CitiesViewModel

@Composable
fun CitiesScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: CitiesViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.sendEvent(CitiesEvent.GetCities)
    }

    Column (
        modifier = modifier
            .fillMaxSize()
    ) {

        if (state.isLoading) {
            LoadingScreen()
        }
        SearchComponent(
            onQueryChange = { query ->
                viewModel.sendEvent(CitiesEvent.SearchCity(query, state.data))
            }
        )

        state.filteredList?.let {
            LazyColumn {
                items(
                    items = it,
                    key = {  citi-> citi.id }
                ) { citi ->
                    ItemCity(citi)
                }
            }
        }
    }
}
