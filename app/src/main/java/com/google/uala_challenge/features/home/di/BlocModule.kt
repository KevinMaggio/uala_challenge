@file:Suppress("UNUSED")

package com.google.uala_challenge.features.home.di

import com.google.uala_challenge.features.home.domain.usecase.GetCitiesUseCase
import com.google.uala_challenge.features.home.domain.usecase.SaveFavoriteIdsUseCase
import com.google.uala_challenge.features.home.domain.usecase.SearchCitiesUseCase
import com.google.uala_challenge.features.home.presenter.bloc.CitiesBaseBloc
import com.google.uala_challenge.features.home.presenter.bloc.HandleCitiesBloc
import com.google.uala_challenge.features.home.presenter.bloc.HandleSearchCitiesBloc
import com.google.uala_challenge.features.home.presenter.bloc.HandleSelectCityBloc
import com.google.uala_challenge.features.home.presenter.bloc.HandleToggleFavoriteBloc
import com.google.uala_challenge.features.home.presenter.bloc.HandleToggleFilterFavoritesBloc
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlin.jvm.JvmWildcard
import javax.inject.Named

@Module
@InstallIn(ViewModelComponent::class)
object BlocModule {

    @Provides
    fun provideHandleCitiesBloc(getCitiesUseCase: GetCitiesUseCase): HandleCitiesBloc {
        return HandleCitiesBloc(getCitiesUseCase)
    }

    @Provides
    fun provideHandleSearchCitiesBloc(searchCitiesUseCase: SearchCitiesUseCase): HandleSearchCitiesBloc {
        return HandleSearchCitiesBloc(searchCitiesUseCase)
    }

    @Provides
    fun provideHandleSelectCityBloc(): HandleSelectCityBloc {
        return HandleSelectCityBloc()
    }

    @Provides
    fun provideHandleToggleFavoriteBloc(saveFavoriteIdsUseCase: SaveFavoriteIdsUseCase): HandleToggleFavoriteBloc {
        return HandleToggleFavoriteBloc(saveFavoriteIdsUseCase)
    }

    @Provides
    fun provideHandleToggleFilterFavoritesBloc(searchCitiesUseCase: SearchCitiesUseCase): HandleToggleFilterFavoritesBloc {
        return HandleToggleFilterFavoritesBloc(searchCitiesUseCase)
    }

    @Provides
    @Named("CitiesBlocs")
    fun provideCitiesBlocs(
        handleCitiesBloc: HandleCitiesBloc,
        handleSearchCitiesBloc: HandleSearchCitiesBloc,
        handleSelectCityBloc: HandleSelectCityBloc,
        handleToggleFavoriteBloc: HandleToggleFavoriteBloc,
        handleToggleFilterFavoritesBloc: HandleToggleFilterFavoritesBloc
    ): List<@JvmWildcard CitiesBaseBloc> {
        return listOf(
            handleCitiesBloc,
            handleSearchCitiesBloc,
            handleSelectCityBloc,
            handleToggleFavoriteBloc,
            handleToggleFilterFavoritesBloc
        )
    }
}
