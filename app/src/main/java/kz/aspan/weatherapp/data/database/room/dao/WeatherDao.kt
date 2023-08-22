package kz.aspan.weatherapp.data.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kz.aspan.weatherapp.data.database.room.entity.LastInputDbModel
import kz.aspan.weatherapp.data.database.room.entity.WeatherDbModel
import kz.aspan.weatherapp.domain.models.LastInput
import kz.aspan.weatherapp.domain.models.Weather

@Dao
interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addWeather(weather: List<WeatherDbModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLastInput(lastInput: LastInputDbModel): Long

    @Query("SELECT * FROM last_input ORDER BY id DESC LIMIT 1")
    suspend fun getLastInput(): LastInputDbModel?

    @Query("SELECT * FROM last_input WHERE input ==:input LIMIT 1")
    suspend fun getInput(input: String): LastInputDbModel?

    @Query("SELECT * FROM weather WHERE lastInputId == :id")
    suspend fun getWeatherByLastInputId(id: Long): List<WeatherDbModel>

    @Query("DELETE FROM weather WHERE id != :inputId")
    suspend fun deleteOldWeather(inputId: Long)

    @Query("DELETE FROM last_input WHERE id != :inputId")
    suspend fun deleteOldInput(inputId: Long)


}