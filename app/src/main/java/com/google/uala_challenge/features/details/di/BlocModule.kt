@file:Suppress("UNUSED")
package com.google.uala_challenge.features.details.di

import com.google.uala_challenge.features.details.domain.usecase.GetCityDetailUseCase
import com.google.uala_challenge.features.details.presenter.bloc.DetailBaseBloc
import com.google.uala_challenge.features.details.presenter.bloc.HandleGetDetailBloc
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
    fun provideHandleGetDetailBloc(getCityDetailUseCase: GetCityDetailUseCase): HandleGetDetailBloc {
        return HandleGetDetailBloc(getCityDetailUseCase)
    }

    @Provides
    @Named("DetailBlocs")
    fun provideDetailBlocs(handleGetDetailBloc: HandleGetDetailBloc): List<@JvmWildcard DetailBaseBloc> {
        return listOf(handleGetDetailBloc)
    }
}
