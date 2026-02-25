package com.google.uala_challenge.features.navigation

object Routes {

    const val HOME = "home"
    const val ARG_CITY_ID = "cityId"
    const val DETAIL = "details/{$ARG_CITY_ID}"

    fun detail(cityId: Int): String = "details/$cityId"
}
