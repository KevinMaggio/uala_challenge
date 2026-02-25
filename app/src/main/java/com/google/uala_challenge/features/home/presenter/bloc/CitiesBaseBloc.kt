package com.google.uala_challenge.features.home.presenter.bloc

import com.google.uala_challenge.features.home.domain.model.CitiesState

typealias CitiStateUpdate = suspend (suspend (CitiesState) -> CitiesState) -> Unit

interface CitiesBaseBloc {
    fun canHandle(event: CitiesEvent): Boolean
    suspend fun handle(event: CitiesEvent, update: CitiStateUpdate)
}
