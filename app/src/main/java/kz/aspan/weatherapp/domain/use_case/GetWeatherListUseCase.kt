package kz.aspan.weatherapp.domain.use_case

import kotlinx.coroutines.flow.Flow
import kz.aspan.weatherapp.domain.models.Weather

interface GetWeatherListUseCase {
    suspend operator fun invoke(name:String): Flow<List<Weather>>
}