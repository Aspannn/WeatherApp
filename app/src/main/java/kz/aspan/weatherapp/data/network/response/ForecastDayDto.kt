package kz.aspan.weatherapp.data.network.response


import com.google.gson.annotations.SerializedName

data class ForecastDayDto(
    @SerializedName("day")
    val day: DayDto
)