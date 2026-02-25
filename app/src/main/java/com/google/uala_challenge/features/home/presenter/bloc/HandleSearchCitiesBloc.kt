package com.google.uala_challenge.features.home.presenter.bloc

import com.google.uala_challenge.features.home.domain.usecase.SearchCitiesUseCase
import javax.inject.Inject

class HandleSearchCitiesBloc @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : CitiesBaseBloc {
    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.SearchCity

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.SearchCity) return
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
