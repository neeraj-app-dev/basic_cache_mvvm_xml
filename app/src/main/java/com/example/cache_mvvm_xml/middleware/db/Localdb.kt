package com.example.cache_mvvm_xml.middleware.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LocalDbData::class], version = 1)
abstract class Localdb : RoomDatabase() {
   abstract fun dao() : DbDao
}