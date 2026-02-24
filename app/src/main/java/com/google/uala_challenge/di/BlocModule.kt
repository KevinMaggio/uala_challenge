@file:Suppress("UNUSED")
package com.google.uala_challenge.di

import com.google.uala_challenge.domain.usecase.GetCitiesUseCase
import com.google.uala_challenge.domain.usecase.SearchCitiesUseCase
import com.google.uala_challenge.presenter.bloc.CitiesBaseBloc
import com.google.uala_challenge.presenter.bloc.HandleCitiesBloc
import com.google.uala_challenge.presenter.bloc.HandleSearchCitiesBloc
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
    @Named("CitiesBlocs")
    fun provideCitiesBlocs(
        handleCitiesBloc: HandleCitiesBloc,
        handleSearchCitiesBloc: HandleSearchCitiesBloc
    ): List<@JvmWildcard CitiesBaseBloc> {
        return listOf(handleCitiesBloc, handleSearchCitiesBloc)
    }
}
