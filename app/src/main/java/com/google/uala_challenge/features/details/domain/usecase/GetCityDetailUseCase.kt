package com.google.uala_challenge.features.details.domain.usecase

import com.google.uala_challenge.features.details.domain.model.CityDetailModel
import com.google.uala_challenge.features.details.domain.repository.CityDetailRepository
import javax.inject.Inject

class GetCityDetailUseCase @Inject constructor(
    private val repository: CityDetailRepository
) {

    suspend operator fun invoke(id: Int): CityDetailModel? = repository.getDetailById(id)
}
