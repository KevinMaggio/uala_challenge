package com.google.uala_challenge.features.details.di

import com.google.uala_challenge.features.details.data.repository.CityDetailRepositoryImpl
import com.google.uala_challenge.features.details.domain.repository.CityDetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindCityDetailRepository(impl: CityDetailRepositoryImpl): CityDetailRepository
}
