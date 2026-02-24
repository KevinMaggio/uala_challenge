package com.google.uala_challenge.data.repository

import com.google.uala_challenge.data.dataSource.remote.CitiesService
import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.data.dto.CityResponse
import com.google.uala_challenge.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesService: CitiesService
) : CitiesRepository {
    override suspend fun getAllCities(): AsyncResult<List<CityResponse>, Exception> {
        return citiesService.getCities()
    }
}
