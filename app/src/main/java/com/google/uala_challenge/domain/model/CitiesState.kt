package com.google.uala_challenge.domain.model

data class CitiesState (
    var data: CitiesModel? = null,
    var error : Boolean = false,
    val loading: Boolean = true
)