package com.google.uala_challenge.domain.usecase

import com.google.uala_challenge.domain.repository.CitiesRepository
import javax.inject.Inject

class SaveFavoriteIdsUseCase @Inject constructor(
    private val repository: CitiesRepository
) {
    suspend operator fun invoke(ids: Set<Int>) {
        repository.saveFavoriteIds(ids)
    }
}
