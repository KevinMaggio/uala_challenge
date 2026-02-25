package com.google.uala_challenge.features.home.presenter.bloc

import com.google.uala_challenge.features.home.domain.model.CityModel

sealed class CitiesEvent {
    object GetCities : CitiesEvent()
    data class SearchCity(val query: String?, val cities: List<CityModel>?) : CitiesEvent()
    data class SelectCity(val city: CityModel?) : CitiesEvent()
    data class ToggleFavorite(val cityId: Int) : CitiesEvent()
    object ToggleFilterFavorites : CitiesEvent()
}