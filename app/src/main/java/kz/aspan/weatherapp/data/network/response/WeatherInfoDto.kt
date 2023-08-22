package kz.aspan.weatherapp.data.network.response


import com.google.gson.annotations.SerializedName

data class WeatherInfoDto(
    @SerializedName("current")
    val current: CurrentDto,
    @SerializedName("forecast")
    val forecast: ForecastDto
)