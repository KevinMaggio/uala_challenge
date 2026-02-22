package com.google.uala_challenge.core.network

import com.google.uala_challenge.core.constants.BASE_URL
import com.google.uala_challenge.data.dataSource.remote.CitiesAPI
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitProvider {

    fun create(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getInstance(): CitiesAPI {
        return this.create(OKHttpProvider.create()).create<CitiesAPI>()
    }
}