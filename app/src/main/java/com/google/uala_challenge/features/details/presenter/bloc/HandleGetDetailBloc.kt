package com.google.uala_challenge.features.details.presenter.bloc

import com.google.uala_challenge.features.details.domain.usecase.GetCityDetailUseCase
import javax.inject.Inject

class HandleGetDetailBloc @Inject constructor(
    private val getCityDetailUseCase: GetCityDetailUseCase
) : DetailBaseBloc {

    override fun canHandle(event: DetailEvent): Boolean = event is DetailEvent.GetDetail

    override suspend fun handle(
        event: DetailEvent,
        update: DetailStateUpdate
    ) {
        if (event !is DetailEvent.GetDetail) return
        update { it.copy(isLoading = true, error = false) }
        val detail = getCityDetailUseCase(event.id)
        update {
            it.copy(
                detail = detail,
                isLoading = false,
                error = detail == null
            )
        }
    }
}
