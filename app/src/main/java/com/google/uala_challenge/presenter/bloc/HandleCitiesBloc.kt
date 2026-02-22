package com.google.uala_challenge.presenter.bloc

import com.google.uala_challenge.data.dto.AsyncResult
import com.google.uala_challenge.domain.usecase.GetCitiesUseCase

class HandleCitiesBloc(val getCitiesUseCase: GetCitiesUseCase = GetCitiesUseCase()): CitiesBaseBloc {
    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.GetCities

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.GetCities) return
        val result = getCitiesUseCase()

        when(result){
            is AsyncResult.Failure -> {
                update{
                    it.copy(
                        error = true,
                        loading = false
                    )
                }
            }

            is AsyncResult.Success -> {
                update{
                    it.copy(
                        data = result.value,
                        loading = false
                    )
                }
            }
        }
    }
}