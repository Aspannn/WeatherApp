package kz.aspan.weatherapp.data.mapper

import kz.aspan.weatherapp.data.database.room.entity.WeatherDbModel
import kz.aspan.weatherapp.data.network.response.LocationDto
import kz.aspan.weatherapp.data.network.response.WeatherInfoDto
import kz.aspan.weatherapp.domain.models.Weather
import javax.inject.Inject

class WeatherMapper @Inject constructor() {

    fun fromRemoteToDomain(
        locationDto: LocationDto,
        weatherInfoDto: WeatherInfoDto
    ): Weather {
        val day = weatherInfoDto.forecast.forecastDay.firstOrNull()?.day
        return Weather(
            id = locationDto.id,
            location = locationDto.name,
            country = locationDto.country,
            maxTemp = day?.maxTemp?.toInt(),
            minTemp = day?.minTemp?.toInt(),
            currentTemp = weatherInfoDto.current.tempC.toInt(),
        )
    }

    fun fromDomainToDbModel(
        lastInputId: Long,
        weather: Weather
    ): WeatherDbModel {
        return WeatherDbModel(
            id = weather.id,
            location = weather.location,
            country = weather.country,
            maxTemp = weather.maxTemp,
            minTemp = weather.minTemp,
            currentTemp = weather.currentTemp,
            lastInputId = lastInputId
        )
    }

    fun fromDbModelToDomain(list: List<WeatherDbModel>): List<Weather> {
        return list.map { fromDbModelToDomain(it) }
    }

    fun fromDbModelToDomain(weatherDbModel: WeatherDbModel): Weather {
        return Weather(
            id = weatherDbModel.id,
            maxTemp = weatherDbModel.maxTemp,
            minTemp = weatherDbModel.minTemp,
            currentTemp = weatherDbModel.currentTemp,
            location = weatherDbModel.location,
            country = weatherDbModel.country
        )
    }
}