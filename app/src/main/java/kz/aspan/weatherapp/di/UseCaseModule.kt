package kz.aspan.weatherapp.di

import dagger.Binds
import dagger.Module
import kz.aspan.weatherapp.data.use_case.GetLastInputUseCaseImpl
import kz.aspan.weatherapp.data.use_case.GetWeatherListUseCaseImpl
import kz.aspan.weatherapp.domain.use_case.GetLastInputUseCase
import kz.aspan.weatherapp.domain.use_case.GetWeatherListUseCase

@Module
interface UseCaseModule {

    @Binds
    fun bindGetLastInputUseCase(impl: GetLastInputUseCaseImpl): GetLastInputUseCase


    @Binds
    fun bindGetWeatherUseCase(impl: GetWeatherListUseCaseImpl): GetWeatherListUseCase
}