package kz.aspan.weatherapp.domain.use_case

import kz.aspan.weatherapp.domain.models.LastInput
import kz.aspan.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

interface GetLastInputUseCase {
    suspend operator fun invoke():LastInput?
}