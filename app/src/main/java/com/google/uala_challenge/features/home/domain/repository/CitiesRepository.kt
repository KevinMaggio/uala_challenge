package com.google.uala_challenge.features.home.domain.repository

import com.google.uala_challenge.features.home.data.dto.AsyncResult
import com.google.uala_challenge.features.home.data.dto.CityResponse

interface CitiesRepository {

    suspend fun getAllCities(): AsyncResult<List<CityResponse>, Exception>

    suspend fun getFavoriteIds(): Set<Int>

    suspend fun saveFavoriteIds(ids: Set<Int>)
}