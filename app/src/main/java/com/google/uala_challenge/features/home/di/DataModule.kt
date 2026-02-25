package com.google.uala_challenge.features.home.di

import com.google.uala_challenge.core.network.ConnectivityChecker
import com.google.uala_challenge.core.network.ConnectivityCheckerImpl
import com.google.uala_challenge.features.home.data.repository.CitiesRepositoryImpl
import com.google.uala_challenge.features.home.domain.repository.CitiesRepository
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

    @Binds
    @Singleton
    abstract fun bindConnectivityChecker(impl: ConnectivityCheckerImpl): ConnectivityChecker
}
