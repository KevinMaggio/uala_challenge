package com.google.uala_challenge.features.home.presenter.bloc

import com.google.uala_challenge.features.home.domain.usecase.SearchCitiesUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class HandleSearchCitiesBloc @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : CitiesBaseBloc {
    private var searchJob: Job? = null

    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.SearchCity

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.SearchCity) return

        searchJob?.cancel()

        coroutineScope {
            searchJob = launch {
                delay(300)
                
                update { current ->
                    val baseList = if (current.showOnlyFavorites) {
                        (event.cities ?: current.data).orEmpty().filter { it.isFavorite }
                    } else {
                        (event.cities ?: current.data).orEmpty()
                    }
                    val filteredList = searchCitiesUseCase(event.query, baseList)
                    current.copy(filteredList = filteredList, query = event.query)
                }
            }
        }
    }
}
