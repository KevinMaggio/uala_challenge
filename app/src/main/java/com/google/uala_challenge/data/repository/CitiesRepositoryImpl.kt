package com.google.uala_challenge.data.repository

import com.google.uala_challenge.core.network.ConnectivityChecker
import com.google.uala_challenge.core.network.NoConnectivityException
import com.google.uala_challenge.data.dataSource.remote.CitiesService
import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.data.dto.CityResponse
import com.google.uala_challenge.domain.repository.CitiesRepository
import javax.inject.Inject

class CitiesRepositoryImpl @Inject constructor(
    private val citiesService: CitiesService,
    private val connectivityChecker: ConnectivityChecker
) : CitiesRepository {
    override suspend fun getAllCities(): AsyncResult<List<CityResponse>, Exception> {
        if (!connectivityChecker.hasInternet()) {
            return AsyncResult.Failure(NoConnectivityException())
        }
        return citiesService.getCities()
    }
}
