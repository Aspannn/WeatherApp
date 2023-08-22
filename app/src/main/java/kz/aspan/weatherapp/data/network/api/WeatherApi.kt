package kz.aspan.weatherapp.data.network.api

import kz.aspan.weatherapp.data.network.response.LocationDto
import kz.aspan.weatherapp.data.network.response.WeatherInfoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("/v1/search.json")
    suspend fun getLocations(
        @Query("q") name: String
    ): List<LocationDto>


    @GET("/v1/forecast.json")
    suspend fun getForecast(
        @Query("q") name: String,
    ): WeatherInfoDto

}