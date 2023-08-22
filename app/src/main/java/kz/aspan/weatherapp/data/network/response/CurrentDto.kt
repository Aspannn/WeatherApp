package kz.aspan.weatherapp.data.network.response


import com.google.gson.annotations.SerializedName

data class CurrentDto(
    @SerializedName("temp_c")
    val tempC: Double
)