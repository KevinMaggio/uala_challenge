package com.google.uala_challenge.data.repository

import com.google.uala_challenge.data.dataSource.remote.CitiesService
import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.data.dto.CitiesResponse
import com.google.uala_challenge.domain.repository.CitiesRepository

class CitiesRepositoryImpl(val citiesService: CitiesService = CitiesService()) : CitiesRepository {
    override suspend fun getAllCities(): AsyncResult<CitiesResponse, Exception> {
        return citiesService.getCities()
    }
}
