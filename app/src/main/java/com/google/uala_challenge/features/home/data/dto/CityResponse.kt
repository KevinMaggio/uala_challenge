package com.google.uala_challenge.features.home.data.dto

import com.google.gson.annotations.SerializedName

data class CityResponse(
    @SerializedName("country")
    val country: String?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("_id")
    val id: Int?,
    @SerializedName("coord")
    val coordinates: Coordinates
) {
    data class Coordinates(
        @SerializedName("lat")
        val latitude: Double?,
        @SerializedName("lon")
        val longitude: Double?
    )
}
