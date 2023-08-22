package kz.aspan.weatherapp.data.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import kz.aspan.weatherapp.data.database.room.dao.WeatherDao
import kz.aspan.weatherapp.data.database.room.entity.LastInputDbModel
import kz.aspan.weatherapp.data.database.room.entity.WeatherDbModel

@Database(
    entities = [
        WeatherDbModel::class,
        LastInputDbModel::class
    ], version = 1
)
abstract class WeatherRoomDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}