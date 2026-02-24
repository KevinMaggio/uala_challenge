package com.google.uala_challenge.di

import com.google.uala_challenge.data.repository.CitiesRepositoryImpl
import com.google.uala_challenge.domain.repository.CitiesRepository
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
    abstract fun bindCitiesRepository(impl: CitiesRepositoryImpl): CitiesRepository
}
