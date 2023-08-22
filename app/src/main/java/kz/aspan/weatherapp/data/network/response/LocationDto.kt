package kz.aspan.weatherapp.data.network.response


import com.google.gson.annotations.SerializedName

data class LocationDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String
)