package com.google.uala_challenge.data.dataSource.remote

import com.google.uala_challenge.core.constants.ERROR_EMPTY_RESPONSE
import com.google.uala_challenge.core.network.RetrofitProvider
import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.data.dto.CityResponse

class CitiesService(val citiesAPI: CitiesAPI = RetrofitProvider.getInstance()) {

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
