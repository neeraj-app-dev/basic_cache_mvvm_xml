package com.example.cache_mvvm_xml.middleware.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache_table")
data class LocalDbData(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userId: Int,
    val title: String,
    val body: String,
)

