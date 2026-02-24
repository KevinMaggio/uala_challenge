package com.google.uala_challenge.domain.repository

import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.data.dto.CityResponse

interface CitiesRepository {

    suspend fun getAllCities(): AsyncResult<List<CityResponse>, Exception>

    suspend fun getFavoriteIds(): Set<Int>

    suspend fun saveFavoriteIds(ids: Set<Int>)
}