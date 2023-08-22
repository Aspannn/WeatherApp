package kz.aspan.weatherapp.data.network.response


import com.google.gson.annotations.SerializedName

data class ForecastDto(
    @SerializedName("forecastday")
    val forecastDay: List<ForecastDayDto>
)