package com.google.uala_challenge.presenter.bloc

import javax.inject.Inject

class HandleSelectCityBloc @Inject constructor() : CitiesBaseBloc {
    override fun canHandle(event: CitiesEvent): Boolean = event is CitiesEvent.SelectCity

    override suspend fun handle(
        event: CitiesEvent,
        update: CitiStateUpdate
    ) {
        if (event !is CitiesEvent.SelectCity) return
        update { it.copy(selectedCity = event.city) }
    }
}
