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
                // Ordenamos por la clave pre-calculada para habilitar búsqueda binaria O(log n).
                // Esto es mucho más eficiente que ordenar con un comparador complejo en cada búsqueda.
                val fromApi = result.value.toModel().sortedBy { it.searchKey }
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
