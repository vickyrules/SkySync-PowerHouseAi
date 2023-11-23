package com.example.skysync.data.room

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface EntityDao {

    @Upsert
    suspend fun insertUpdateItem(item: EntityItem)
    @Query("SELECT * FROM EntityItem WHERE name = :name  ")
    fun getItemByCity(name: String): Flow<EntityItem>
    @Query("SELECT * FROM EntityItem WHERE lat = :lat & lon =:lon")
    fun getItemByLatLon(lat: Double, lon: Double): Flow<EntityItem>
    @Query("SELECT * FROM EntityItem")
    fun getAllItems(): Flow<List<EntityItem>>
}