package kz.aspan.weatherapp.data.repository

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kz.aspan.weatherapp.data.database.room.dao.WeatherDao
import kz.aspan.weatherapp.data.database.room.entity.LastInputDbModel
import kz.aspan.weatherapp.data.mapper.LastInputMapper
import kz.aspan.weatherapp.data.mapper.WeatherMapper
import kz.aspan.weatherapp.data.network.api.WeatherApi
import kz.aspan.weatherapp.data.network.response.LocationDto
import kz.aspan.weatherapp.domain.models.LastInput
import kz.aspan.weatherapp.domain.models.Weather
import kz.aspan.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApi,
    private val weatherDao: WeatherDao,
    private val weatherMapper: WeatherMapper,
    private val lastInputMapper: LastInputMapper
) : WeatherRepository {
    override suspend fun getLastInput(): LastInput? {
        val result = weatherDao.getLastInput()
        return if (result != null) {
            lastInputMapper.fromDbModelToDomain(result)
        } else {
            null
        }

    }

    override suspend fun getWeather(name: String) = flow {
        val input = weatherDao.getInput(name)
        if (input != null) {
            val weatherList = getWeatherFromDb(input.id)
            emit(weatherList)
            if (input.createAt + ONE_HOUR_IN_MILLISECONDS < System.currentTimeMillis()) {
                emit(getWeatherRemote(name))
            }
        } else {
            emit(getWeatherRemote(name))
        }
    }

    override suspend fun getWeatherFromDb(id: Long): List<Weather> {
        val result = weatherDao.getWeatherByLastInputId(id)
        return weatherMapper.fromDbModelToDomain(result)
    }


    private suspend fun getWeatherRemote(name: String): List<Weather> {
        val locationResponse = weatherApi.getLocations(name)
        val lastInputId = saveLastInputToDb(name)
        deleteOldData(lastInputId)
        val listOfWeather = getListOfWeatherByLocation(locationResponse)
        saveWeatherToDb(lastInputId, listOfWeather)
        return listOfWeather
    }

    private suspend fun getListOfWeatherByLocation(locations: List<LocationDto>) = coroutineScope {
        val result = mutableListOf<Weather>()
        locations.forEach { locationDto ->
            launch {
                val weatherResponse = weatherApi.getForecast(locationDto.name)
                val weather = weatherMapper.fromRemoteToDomain(
                    locationDto,
                    weatherResponse
                )
                result.add(weather)
            }
        }
        result
    }

    private suspend fun deleteOldData(lastInputId: Long) {
        weatherDao.deleteOldInput(lastInputId)
        weatherDao.deleteOldWeather(lastInputId)
    }

    private suspend fun saveWeatherToDb(lastInputId: Long, list: List<Weather>) {
        val weatherDb = list.map { weatherMapper.fromDomainToDbModel(lastInputId, it) }
        weatherDao.addWeather(weatherDb)
    }

    private suspend fun saveLastInputToDb(input: String): Long {
        val lastInputDbModel = LastInputDbModel(
            id = LastInput.UNDEFINED_ID,
            input = input,
            createAt = System.currentTimeMillis()
        )
        return weatherDao.addLastInput(lastInputDbModel)
    }

    companion object {
        private const val ONE_HOUR_IN_MILLISECONDS = 3_600_000
    }
}