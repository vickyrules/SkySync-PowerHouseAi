package com.example.skysync.di

import com.example.skysync.data.repository.WeatherRepository
import com.example.skysync.data.repository.WeatherRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindModule {
    @Binds
    abstract fun bindRepository(impl: WeatherRepositoryImpl): WeatherRepository
}