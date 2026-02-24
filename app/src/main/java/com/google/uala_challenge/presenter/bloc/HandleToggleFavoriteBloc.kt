package com.google.uala_challenge.presenter.bloc

import com.google.uala_challenge.domain.usecase.SaveFavoriteIdsUseCase
import javax.inject.Inject

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
            val newFiltered = current.filteredList?.map { city ->
                if (city.id == event.cityId) city.copy(isFavorite = !city.isFavorite) else city
            } ?: current.filteredList
            val newFavoriteIds = newData.filter { it.isFavorite }.map { it.id }.toSet()
            saveFavoriteIdsUseCase(newFavoriteIds)
            current.copy(data = newData, filteredList = newFiltered)
        }
    }
}
