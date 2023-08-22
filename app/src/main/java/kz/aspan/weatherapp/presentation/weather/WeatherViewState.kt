package kz.aspan.weatherapp.presentation.weather


import kz.aspan.weatherapp.domain.models.Weather

data class WeatherViewState(
    val isLoading: Boolean = false,
    val listOfWeather: List<Weather>? = null,
    val lastInput: String? = null,
)