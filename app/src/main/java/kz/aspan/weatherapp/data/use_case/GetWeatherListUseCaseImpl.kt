package kz.aspan.weatherapp.data.use_case

import kz.aspan.weatherapp.domain.repository.WeatherRepository
import kz.aspan.weatherapp.domain.use_case.GetWeatherListUseCase
import javax.inject.Inject

class GetWeatherListUseCaseImpl @Inject constructor(
    private val repository: WeatherRepository
) : GetWeatherListUseCase {
    override suspend operator fun invoke(name: String) = repository.getWeather(name)
}