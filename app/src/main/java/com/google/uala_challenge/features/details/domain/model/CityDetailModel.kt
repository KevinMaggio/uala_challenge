package com.google.uala_challenge.features.details.domain.model

data class CityDetailModel(
    val id: Int,
    val name: String,
    val country: String,
    val coordinates: Coordinates,
    val population: String,
    val timezone: String,
    val description: String
) {
    data class Coordinates(
        val latitude: Double,
        val longitude: Double
    )
}
