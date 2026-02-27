package com.google.uala_challenge.features.home.domain.usecase

import com.google.uala_challenge.features.home.domain.model.CityModel
import org.junit.Assert.assertEquals
import org.junit.Test

class SearchCitiesUseCaseTest {

    private val useCase = SearchCitiesUseCase()

    private fun city(name: String, country: String) = CityModel(
        country = country,
        name = name,
        id = 0,
        coordinates = CityModel.Coordinates(0.0, 0.0),
        searchKey = "${name.lowercase()}, ${country.lowercase()}"
    )

    @Test
    fun `invoke returns filtered list correctly`() {
        val list = listOf(
            city("Alabama", "US"),
            city("Albuquerque", "US"),
            city("Sydney", "AU")
        ).sortedBy { it.searchKey }

        val result = useCase("Al", list)

        assertEquals(2, result?.size)
        assertEquals("Alabama", result?.get(0)?.name)
        assertEquals("Albuquerque", result?.get(1)?.name)
    }

    @Test
    fun `invoke returns full list when query is empty`() {
        val list = listOf(city("A", "US"), city("B", "US"))
        val result = useCase("", list)
        assertEquals(2, result?.size)
    }

    @Test
    fun `invoke returns empty list when no match`() {
        val list = listOf(city("A", "US"))
        val result = useCase("Z", list)
        assertEquals(0, result?.size)
    }
}
