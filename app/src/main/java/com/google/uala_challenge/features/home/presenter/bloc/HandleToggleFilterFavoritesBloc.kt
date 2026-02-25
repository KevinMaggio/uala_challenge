package com.google.uala_challenge.features.home.presenter.bloc

import com.google.uala_challenge.features.home.domain.usecase.SearchCitiesUseCase
import javax.inject.Inject
import kotlin.collections.orEmpty

class HandleToggleFilterFavoritesBloc @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : CitiesBaseBloc {

    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.ToggleFilterFavorites

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.ToggleFilterFavorites) return
        update { current ->
            val newShow = !current.showOnlyFavorites
            val baseList = if (newShow) {
                current.data.orEmpty().filter { it.isFavorite }
            } else {
                current.data.orEmpty()
            }
            val filteredList = searchCitiesUseCase(current.query, baseList)
            current.copy(showOnlyFavorites = newShow, filteredList = filteredList)
        }
    }
}
