package com.google.uala_challenge.features.home.domain.usecase

import com.google.uala_challenge.features.home.data.dto.AsyncResult
import com.google.uala_challenge.features.home.domain.mapper.toModel
import com.google.uala_challenge.features.home.domain.model.CityModel
import com.google.uala_challenge.features.home.domain.repository.CitiesRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CitiesRepository
) {
    suspend operator fun invoke(): AsyncResult<List<CityModel>, Exception> {
        return when (val result = repository.getAllCities()) {
            is AsyncResult.Success -> {
                val fromApi = result.value.toModel().sortedWith(
                    compareBy({ it.name.lowercase() }, { it.country.lowercase() })
                )
                val favoriteIds = repository.getFavoriteIds()
                val merged = fromApi.map { city ->
                    city.copy(isFavorite = city.id in favoriteIds)
                }
                AsyncResult.Success(merged)
            }
            is AsyncResult.Failure -> AsyncResult.Failure(error = result.error)
        }
    }
}
