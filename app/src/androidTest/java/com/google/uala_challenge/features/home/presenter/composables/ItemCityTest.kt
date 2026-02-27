package com.google.uala_challenge.features.home.presenter.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.google.uala_challenge.features.home.domain.model.CityModel
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

class ItemCityTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun itemCityDisplaysCorrectInformation() {
        val city = CityModel(
            country = "US",
            name = "Alabama",
            id = 1,
            coordinates = CityModel.Coordinates(10.0, 20.0),
            isFavorite = false,
            searchKey = "alabama, us"
        )

        composeTestRule.setContent {
            ItemCity(city = city)
        }

        composeTestRule.onNodeWithText("Alabama").assertIsDisplayed()
        composeTestRule.onNodeWithText("US").assertIsDisplayed()
        // El texto exacto depende de cómo se formatea en la UI, ajustamos según ItemCity.kt
        composeTestRule.onNodeWithText("Coord: 10.00, 20.00").assertIsDisplayed()
    }

    @Test
    fun itemCityClickCallbacksWork() {
        var favoriteClicked = false
        var chevronClicked = false

        val city = CityModel(
            country = "US",
            name = "Alabama",
            id = 1,
            coordinates = CityModel.Coordinates(10.0, 20.0),
            isFavorite = false,
            searchKey = "alabama, us"
        )

        composeTestRule.setContent {
            ItemCity(
                city = city,
                onFavoriteClick = { favoriteClicked = true },
                onChevronClick = { chevronClicked = true }
            )
        }

        // Buscamos por content description
        composeTestRule.onNodeWithContentDescription("Agregar a favoritos").performClick()
        assertTrue(favoriteClicked)

        composeTestRule.onNodeWithContentDescription("Ver detalle").performClick()
        assertTrue(chevronClicked)
    }
}
