package kz.aspan.weatherapp.domain.models

data class Weather(
    val id:Long,
    val maxTemp: Int?,
    val minTemp: Int?,
    val currentTemp: Int,
    val location:String,
    val country:String
)
