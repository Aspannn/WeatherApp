package kz.aspan.weatherapp.data.mapper

import kz.aspan.weatherapp.data.database.room.entity.LastInputDbModel
import kz.aspan.weatherapp.domain.models.LastInput
import javax.inject.Inject

class LastInputMapper @Inject constructor(){

    fun fromDomainToDbModel(lastInput: LastInput):LastInputDbModel{
        return LastInputDbModel(
            id = lastInput.id,
            input = lastInput.input,
            createAt = lastInput.createAt
        )
    }

    fun fromDbModelToDomain(lastInputDbModel: LastInputDbModel):LastInput{
        return LastInput(
            id = lastInputDbModel.id,
            input = lastInputDbModel.input,
            createAt = lastInputDbModel.createAt
        )
    }

}