package com.google.uala_challenge.features.home.domain.utils

import com.google.uala_challenge.features.home.domain.model.CityModel
import org.junit.Assert.assertEquals
import org.junit.Test

class PrefixSearchUtilsTest {

    private fun city(name: String, country: String) = CityModel(
        country = country,
        name = name,
        id = 0,
        coordinates = CityModel.Coordinates(0.0, 0.0),
        searchKey = "${name.lowercase()}, ${country.lowercase()}"
    )

    @Test
    fun `findRangeByPrefix returns correct range for single match`() {
        val list = listOf(
            city("Alabama", "US"),
            city("Albuquerque", "US"),
            city("Anaheim", "US"),
            city("Arizona", "US"),
            city("Sydney", "AU")
        ).sortedBy { it.searchKey }

        val range = findRangeByPrefix(list, "albu")

        assertEquals(1, range.last - range.first + 1)
        assertEquals("Albuquerque", list[range.first].name)
    }

    @Test
    fun `findRangeByPrefix returns correct range for multiple matches`() {
        val list = listOf(
            city("Alabama", "US"),
            city("Albuquerque", "US"),
            city("Anaheim", "US"),
            city("Arizona", "US"),
            city("Sydney", "AU")
        ).sortedBy { it.searchKey }

        val range = findRangeByPrefix(list, "al")

        assertEquals(2, range.last - range.first + 1)
        assertEquals("Alabama", list[range.first].name)
        assertEquals("Albuquerque", list[range.last].name)
    }

    @Test
    fun `findRangeByPrefix returns empty range when no match`() {
        val list = listOf(
            city("Alabama", "US"),
            city("Sydney", "AU")
        ).sortedBy { it.searchKey }

        val range = findRangeByPrefix(list, "z")

        assertEquals(true, range.isEmpty())
    }

    @Test
    fun `findRangeByPrefix handles empty list`() {
        val list = emptyList<CityModel>()
        val range = findRangeByPrefix(list, "a")
        assertEquals(true, range.isEmpty())
    }
}
