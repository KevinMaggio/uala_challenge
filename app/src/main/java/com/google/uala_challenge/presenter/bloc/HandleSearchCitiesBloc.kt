package com.google.uala_challenge.presenter.bloc

import com.google.uala_challenge.domain.usecase.SearchCitiesUseCase
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged

class HandleSearchCitiesBloc(val searchCitiesUseCase: SearchCitiesUseCase = SearchCitiesUseCase()) :
    CitiesBaseBloc {
    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.SearchCity

    @OptIn(FlowPreview::class)
    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.SearchCity) return

        val searchQuery = MutableStateFlow(event.query)

        searchQuery
            .debounce(300L)
            .distinctUntilChanged()
            .collect { searchQuery ->
                val filteredList = searchCitiesUseCase(searchQuery, event.cities)
                update {
                    it.copy(
                        filteredList = filteredList
                    )
                }
            }
    }
}
