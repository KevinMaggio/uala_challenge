package com.google.uala_challenge.features.details.data.repository

import com.google.uala_challenge.features.details.data.dataSource.local.CityDetailLocalDataSource
import com.google.uala_challenge.features.details.domain.model.CityDetailModel
import com.google.uala_challenge.features.details.domain.repository.CityDetailRepository
import javax.inject.Inject

class CityDetailRepositoryImpl @Inject constructor(
    private val localDataSource: CityDetailLocalDataSource
) : CityDetailRepository {

    override suspend fun getDetailById(id: Int): CityDetailModel? =
        localDataSource.getDetailById(id)
}
