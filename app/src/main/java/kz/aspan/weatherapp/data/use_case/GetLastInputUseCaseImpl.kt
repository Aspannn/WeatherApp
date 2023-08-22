package kz.aspan.weatherapp.data.use_case

import kz.aspan.weatherapp.domain.models.LastInput
import kz.aspan.weatherapp.domain.repository.WeatherRepository
import kz.aspan.weatherapp.domain.use_case.GetLastInputUseCase
import javax.inject.Inject

class GetLastInputUseCaseImpl @Inject constructor(
    private val repository: WeatherRepository
) :GetLastInputUseCase{
    override suspend fun invoke(): LastInput? {
        return repository.getLastInput()
    }
}