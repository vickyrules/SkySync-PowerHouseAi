package com.example.skysync.data.room

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [EntityItem::class], version = 1, exportSchema = true)
abstract class SkySyncDatabase : RoomDatabase() {
    abstract fun EntityDao(): EntityDao
}