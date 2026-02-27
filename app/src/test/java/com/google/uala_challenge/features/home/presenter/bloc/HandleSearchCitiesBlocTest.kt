package com.google.uala_challenge.features.home.presenter.bloc

import com.google.uala_challenge.features.home.domain.model.CitiesState
import com.google.uala_challenge.features.home.domain.model.CityModel
import com.google.uala_challenge.features.home.domain.usecase.SearchCitiesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.advanceTimeBy
import org.junit.Assert.assertEquals
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HandleSearchCitiesBlocTest {

    private val useCase = SearchCitiesUseCase()
    private val bloc = HandleSearchCitiesBloc(useCase)

    private fun city(name: String, country: String) = CityModel(
        country = country,
        name = name,
        id = 0,
        coordinates = CityModel.Coordinates(0.0, 0.0),
        searchKey = "${name.lowercase()}, ${country.lowercase()}"
    )

    @Test
    fun `debounce works correctly`() = runTest {
        val list = listOf(
            city("Alabama", "US"),
            city("Albuquerque", "US"),
            city("Sydney", "AU")
        ).sortedBy { it.searchKey }

        val initialState = CitiesState(data = list)
        var currentState = initialState

        val update: CitiStateUpdate = { reducer ->
            currentState = reducer(currentState)
        }

        bloc.handle(CitiesEvent.SearchCity("A", list), update)
        
        advanceTimeBy(100)
        bloc.handle(CitiesEvent.SearchCity("Al", list), update)

        advanceTimeBy(100)
        bloc.handle(CitiesEvent.SearchCity("Alb", list), update)

        advanceTimeBy(301)

        assertEquals("Alb", currentState.query)
        assertEquals(1, currentState.filteredList?.size)
    }
}
