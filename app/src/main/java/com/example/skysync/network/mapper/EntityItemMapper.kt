package com.example.skysync.network.mapper
import com.example.skysync.data.room.EntityItem
import com.example.skysync.network.model.ApiResponse

object EntityItemMapper : EntityMapper<ApiResponse, EntityItem> {

    override fun asEntity(domain: ApiResponse): EntityItem {
        return EntityItem(
            id = domain.sys?.id?:0 ,
            temp = domain.main?.temp?:10.0,
            minTemp = domain.main?.tempMin?:0.0,
            maxTemp = domain.main?.tempMax?:10.0,
            name = domain.name?:"Delhi",
            country = domain.sys?.country?:"In",
            rain = domain.rain?.h?: 10.0,
            windSpeed = domain.wind?.speed?:1.0,
            cloud = domain.clouds?.all?:10,
            feelsLike = domain.main?.feelsLike?:5.0,
            description = domain.weather?.first()?.description?:"",
            iconCode = domain.weather?.first()?.icon?:"10d",
            lat = domain.coord?.lat?:0.0,
            lon = domain.coord?.lon?:0.0
        )
    }
}

fun ApiResponse.asEntity(): EntityItem {
    return EntityItemMapper.asEntity(this)
}


