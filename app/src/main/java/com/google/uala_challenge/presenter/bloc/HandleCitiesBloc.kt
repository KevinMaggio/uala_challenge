package com.google.uala_challenge.presenter.bloc

import com.google.uala_challenge.core.network.NoConnectivityException
import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.domain.usecase.GetCitiesUseCase
import javax.inject.Inject

class HandleCitiesBloc @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : CitiesBaseBloc {
    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.GetCities

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.GetCities) return
        update { it.copy(isLoading = true, error = false, errorInternet = false) }
        val result = getCitiesUseCase()

        when (result) {
            is AsyncResult.Failure -> {
                val isNoInternet = result.error is NoConnectivityException
                update {
                    it.copy(
                        error = !isNoInternet,
                        errorInternet = isNoInternet,
                        isLoading = false
                    )
                }
            }
            is AsyncResult.Success -> {
                update {
                    it.copy(
                        data = result.value,
                        filteredList = result.value,
                        isLoading = false
                    )
                }
            }
        }
    }
}