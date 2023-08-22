package kz.aspan.weatherapp.data.database.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "last_input")
data class LastInputDbModel(
    @PrimaryKey(autoGenerate = true)
    val id:Long,
    val input:String,
    val createAt:Long
)