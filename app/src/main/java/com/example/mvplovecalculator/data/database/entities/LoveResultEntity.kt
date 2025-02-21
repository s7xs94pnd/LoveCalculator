package com.example.mvplovecalculator.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
const val DEFAULT_ID = 0
@Entity(tableName = "love_results")
data class LoveResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = DEFAULT_ID,
    val firstName: String,
    val secondName: String,
    val percentage: String,
    val result: String
)