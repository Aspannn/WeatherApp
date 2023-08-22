package kz.aspan.weatherapp.data.network.response


import com.google.gson.annotations.SerializedName

data class DayDto(
    @SerializedName("maxtemp_c")
    val maxTemp: Double,
    @SerializedName("mintemp_c")
    val minTemp: Double
)