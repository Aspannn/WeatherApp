package kz.aspan.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow
import kz.aspan.weatherapp.domain.models.LastInput
import kz.aspan.weatherapp.domain.models.Weather


interface WeatherRepository {

    suspend fun getLastInput(): LastInput?

    suspend fun getWeatherFromDb(id: Long): List<Weather>

    suspend fun getWeather(name: String): Flow<List<Weather>>

}