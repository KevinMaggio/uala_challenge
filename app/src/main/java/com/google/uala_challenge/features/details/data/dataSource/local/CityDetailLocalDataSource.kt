package com.google.uala_challenge.features.details.data.dataSource.local

import com.google.uala_challenge.features.details.domain.model.CityDetailModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CityDetailLocalDataSource @Inject constructor() {

    suspend fun getDetailById(id: Int): CityDetailModel? = withContext(Dispatchers.IO) {
        delay(300)
        SimulatedDetailData.getDetailForId(id)
    }

    private object SimulatedDetailData {
        private val simulatedDetail = CityDetailModel(
            id = 0,
            name = "Example City Mock",
            country = "AR",
            coordinates = CityDetailModel.Coordinates(latitude = -34.6037, longitude = -58.3816),
            population = "3 100 000",
            timezone = "UTC-3",
            description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's"
        )

        fun getDetailForId(id: Int): CityDetailModel =
            simulatedDetail.copy(id = id, name = "City ID: #$id")
    }
}
