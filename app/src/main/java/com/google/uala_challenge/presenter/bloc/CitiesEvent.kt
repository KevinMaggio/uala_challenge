package com.google.uala_challenge.presenter.bloc

import com.google.uala_challenge.domain.model.CityModel

sealed class CitiesEvent {
    object GetCities : CitiesEvent()
    data class SearchCity(val query: String?, val cities: List<CityModel>?) : CitiesEvent()
    data class SelectCity(val city: CityModel?) : CitiesEvent()
}