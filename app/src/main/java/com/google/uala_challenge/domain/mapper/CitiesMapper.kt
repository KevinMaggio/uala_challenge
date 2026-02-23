package com.google.uala_challenge.domain.mapper

import com.google.uala_challenge.data.dto.CityResponse
import com.google.uala_challenge.domain.model.CitiesModel

fun List<CityResponse>.toModel(): CitiesModel {
    return CitiesModel(
        cities = this.toListCities() ?: emptyList()
    )
}

private fun List<CityResponse>.toListCities(): List<CitiesModel.Citi>? {
    return this.map { citi ->
        CitiesModel.Citi(
            country = citi.country.orEmpty(),
            id = citi.id ?: 0,
            name = citi.name.orEmpty(),
            coordinates = CitiesModel.Citi.Coordinates(
                latitude = citi.coordinates.latitude ?: 0.0,
                longitude = citi.coordinates.longitude ?: 0.0
            )
        )
    }
}
