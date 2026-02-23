package com.google.uala_challenge.data.dataSource.remote

import com.google.uala_challenge.data.dto.CityResponse
import retrofit2.Response
import retrofit2.http.GET

interface CitiesAPI {
    @GET("dce8843a8edbe0b0018b32e137bc2b3a/raw/0996accf70cb0ca0e16f9a99e0ee185fafca7af1/cities.json")
    suspend fun getAllCities(): Response<List<CityResponse>>
}
