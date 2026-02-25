package com.google.uala_challenge.features.home.domain.model

data class CityModel(
    val country: String,
    val name: String,
    val id: Int,
    val coordinates: Coordinates,
    val isFavorite: Boolean = false,
    val searchKey: String = ""
) {
    data class Coordinates(
        val latitude: Double,
        val longitude: Double
    )
}
