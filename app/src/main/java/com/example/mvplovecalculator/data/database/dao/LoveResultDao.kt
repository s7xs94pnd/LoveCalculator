package com.example.mvplovecalculator.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mvplovecalculator.data.database.entities.LoveResultEntity

@Dao
interface LoveResultDao {
    @Insert
    suspend fun insert(loveResult: LoveResultEntity)

    @Query("SELECT * FROM love_results")
    suspend fun getAllResults(): List<LoveResultEntity>
}