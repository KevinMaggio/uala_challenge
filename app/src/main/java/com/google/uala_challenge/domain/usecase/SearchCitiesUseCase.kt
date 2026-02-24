package com.google.uala_challenge.domain.usecase

import com.google.uala_challenge.domain.model.CityModel
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor() {
    operator fun invoke(
        query: String?,
        cities: List<CityModel>?
    ): List<CityModel>? {
        if (query.isNullOrBlank()) return cities

        val lowerQuery = query.lowercase().trim()

        return cities?.let { citiesNotNull ->
            citiesNotNull.filter { city ->
                city.name.lowercase().startsWith(lowerQuery)
            }.sortedWith(
                compareBy({ it.name.lowercase() }, { it.country.lowercase() })
            )
        }
    }
}
