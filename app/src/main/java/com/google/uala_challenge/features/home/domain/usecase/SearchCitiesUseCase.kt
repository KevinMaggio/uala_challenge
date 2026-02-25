package com.google.uala_challenge.features.home.domain.usecase

import com.google.uala_challenge.features.home.domain.model.CityModel
import com.google.uala_challenge.features.home.domain.utils.findRangeByPrefix
import javax.inject.Inject

class SearchCitiesUseCase @Inject constructor() {

    operator fun invoke(
        query: String?,
        cities: List<CityModel>?
    ): List<CityModel>? {
        if (query.isNullOrBlank()) return cities
        val list = cities ?: return null
        if (list.isEmpty()) return list

        val range = findRangeByPrefix(list, query)

        if (range.isEmpty()) return emptyList()

        return list.subList(range.first, range.last + 1)
    }
}
