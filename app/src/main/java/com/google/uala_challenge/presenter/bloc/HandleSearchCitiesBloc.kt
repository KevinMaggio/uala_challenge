package com.google.uala_challenge.presenter.bloc

import com.google.uala_challenge.domain.usecase.SearchCitiesUseCase
import javax.inject.Inject

class HandleSearchCitiesBloc @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
) : CitiesBaseBloc {
    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.SearchCity

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.SearchCity) return
        val filteredList = searchCitiesUseCase(event.query, event.cities)
        update {
            it.copy(filteredList = filteredList)
        }
    }
}
