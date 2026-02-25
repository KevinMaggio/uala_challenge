package com.google.uala_challenge.features.details.domain.repository

import com.google.uala_challenge.features.details.domain.model.CityDetailModel

interface CityDetailRepository {

    suspend fun getDetailById(id: Int): CityDetailModel?
}
