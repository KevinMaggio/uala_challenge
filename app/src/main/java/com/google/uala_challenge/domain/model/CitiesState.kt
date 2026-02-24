package com.google.uala_challenge.domain.model

data class CitiesState(
    val data: List<CityModel>? = null,
    val filteredList: List<CityModel>? = null,
    val selectedCity: CityModel? = null,
    val error: Boolean = false,
    val isLoading: Boolean = true
)