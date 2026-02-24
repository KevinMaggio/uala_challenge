package com.google.uala_challenge.domain.usecase

import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.domain.mapper.toModel
import com.google.uala_challenge.domain.model.CityModel
import com.google.uala_challenge.domain.repository.CitiesRepository
import javax.inject.Inject

class GetCitiesUseCase @Inject constructor(
    private val repository: CitiesRepository
) {
    suspend operator fun invoke(): AsyncResult<List<CityModel>, Exception> {
        return when (val result = repository.getAllCities()) {
            is AsyncResult.Success -> {
                AsyncResult.Success(result.value.toModel())
            }

            is AsyncResult.Failure -> {
                AsyncResult.Failure(error = result.error)
            }
        }
    }
}
