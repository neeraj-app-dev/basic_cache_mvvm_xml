package com.example.cache_mvvm_xml.middleware.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DbDao {

    @Query("select * from cache_table order by userId")
    fun getItems() : List<LocalDbData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItems(data: LocalDbData)

}