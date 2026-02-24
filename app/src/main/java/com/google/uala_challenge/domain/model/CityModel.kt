package com.google.uala_challenge.domain.model

data class CityModel(
    val country: String,
    val name: String,
    val id: Int,
    val coordinates: Coordinates
) {
    data class Coordinates(
        val latitude: Double,
        val longitude: Double
    )
}
