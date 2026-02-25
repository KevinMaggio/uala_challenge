package com.google.uala_challenge.features.home.presenter.bloc

import com.google.uala_challenge.features.home.domain.usecase.SaveFavoriteIdsUseCase
import javax.inject.Inject
import kotlin.collections.filter

class HandleToggleFavoriteBloc @Inject constructor(
    private val saveFavoriteIdsUseCase: SaveFavoriteIdsUseCase
) : CitiesBaseBloc {

    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.ToggleFavorite

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.ToggleFavorite) return
        update { current ->
            val data = current.data ?: return@update current
            val newData = data.map { city ->
                if (city.id == event.cityId) city.copy(isFavorite = !city.isFavorite) else city
            }
            var newFiltered = current.filteredList?.map { city ->
                if (city.id == event.cityId) city.copy(isFavorite = !city.isFavorite) else city
            } ?: current.filteredList
            if (current.showOnlyFavorites && newFiltered != null) {
                newFiltered = newFiltered.filter { it.isFavorite }
            }
            val newFavoriteIds = newData.filter { it.isFavorite }.map { it.id }.toSet()
            saveFavoriteIdsUseCase(newFavoriteIds)
            current.copy(data = newData, filteredList = newFiltered)
        }
    }
}
