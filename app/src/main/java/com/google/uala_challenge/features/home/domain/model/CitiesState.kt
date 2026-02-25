package com.google.uala_challenge.features.home.domain.model

data class CitiesState(
    val data: List<CityModel>? = null,
    val filteredList: List<CityModel>? = null,
    val selectedCity: CityModel? = null,
    val query: String? = null,
    val showOnlyFavorites: Boolean = false,
    val error: Boolean = false,
    val errorInternet: Boolean = false,
    val isLoading: Boolean = true
)