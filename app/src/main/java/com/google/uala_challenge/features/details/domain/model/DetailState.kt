package com.google.uala_challenge.features.details.domain.model

data class DetailState(
    val detail: CityDetailModel? = null,
    val isLoading: Boolean = false,
    val error: Boolean = false
)
