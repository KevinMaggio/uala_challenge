package com.google.uala_challenge.features.home.domain.mapper

import com.google.uala_challenge.features.home.data.dto.CityResponse
import com.google.uala_challenge.features.home.domain.model.CityModel

fun List<CityResponse>.toModel(): List<CityModel> {
    return this.toListCities() ?: emptyList()
}

private fun List<CityResponse>.toListCities(): List<CityModel>? {
    return this.map { citi ->
        CityModel(
            country = citi.country.orEmpty(),
            id = citi.id ?: 0,
            name = citi.name.orEmpty(),
            coordinates = CityModel.Coordinates(
                latitude = citi.coordinates.latitude ?: 0.0,
                longitude = citi.coordinates.longitude ?: 0.0
            ),
            isFavorite = false,
            searchKey = "${citi.name.orEmpty().lowercase()}, ${citi.country.orEmpty().lowercase()}"
        )
    }
}
