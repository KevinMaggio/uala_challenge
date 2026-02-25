package com.google.uala_challenge.features.details.presenter.bloc

sealed class DetailEvent {
    data class GetDetail(val id: Int) : DetailEvent()
}
