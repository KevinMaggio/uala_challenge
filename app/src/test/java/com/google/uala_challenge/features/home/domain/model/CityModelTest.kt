package com.google.uala_challenge.features.home.domain.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CityModelTest {

    @Test
    fun `searchKey is correctly formatted`() {
        val city = CityModel(
            country = "US",
            name = "Alabama",
            id = 1,
            coordinates = CityModel.Coordinates(0.0, 0.0),
            searchKey = "alabama, us"
        )

        assertEquals("alabama, us", city.searchKey)
    }

    @Test
    fun `coordinates data class holds values correctly`() {
        val coords = CityModel.Coordinates(10.5, -20.0)
        assertEquals(10.5, coords.latitude, 0.0)
        assertEquals(-20.0, coords.longitude, 0.0)
    }
}
