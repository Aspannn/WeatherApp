package kz.aspan.weatherapp.data.database.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherDbModel(
    @PrimaryKey
    val id: Long,
    val location:String,
    val country:String,
    val lastInputId:Long,
    val maxTemp: Int?,
    val minTemp: Int?,
    val currentTemp:Int
)
