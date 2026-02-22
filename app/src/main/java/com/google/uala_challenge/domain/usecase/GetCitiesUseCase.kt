package com.google.uala_challenge.domain.usecase

import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.data.repository.CitiesRepositoryImpl
import com.google.uala_challenge.domain.mapper.toModel
import com.google.uala_challenge.domain.model.CitiesModel
import com.google.uala_challenge.domain.repository.CitiesRepository

class GetCitiesUseCase(val repository: CitiesRepository = CitiesRepositoryImpl()) {
    suspend operator fun invoke(): AsyncResult<CitiesModel, Exception> {
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
