package com.google.uala_challenge.features.details.presenter.bloc

import com.google.uala_challenge.features.details.domain.model.DetailState

typealias DetailStateUpdate = suspend (suspend (DetailState) -> DetailState) -> Unit

interface DetailBaseBloc {
    fun canHandle(event: DetailEvent): Boolean
    suspend fun handle(event: DetailEvent, update: DetailStateUpdate)
}
