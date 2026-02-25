package com.google.uala_challenge.features.home.data.dataSource.remote

import com.google.uala_challenge.core.constants.ERROR_EMPTY_RESPONSE
import com.google.uala_challenge.features.home.data.dto.AsyncResult
import javax.inject.Inject
import com.google.uala_challenge.features.home.data.dto.CityResponse

class CitiesService @Inject constructor(
    private val citiesAPI: CitiesAPI
) {

    suspend fun getCities(): AsyncResult<List<CityResponse>, Exception> {
        return try {
            val result = citiesAPI.getAllCities()
            if (result.isSuccessful) {
                result.body()?.let { body ->
                    AsyncResult.Success(body)
                } ?: run {
                    AsyncResult.Failure(Exception(ERROR_EMPTY_RESPONSE))
                }
            } else {
                AsyncResult.Failure(Exception("HTTP ${result.code()}: ${result.message()}"))
            }
        } catch (e: Exception) {
            AsyncResult.Failure(e)
        }
    }
}
