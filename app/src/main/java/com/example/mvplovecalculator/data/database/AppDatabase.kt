package com.example.mvplovecalculator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mvplovecalculator.data.database.dao.LoveResultDao
import com.example.mvplovecalculator.data.database.entities.LoveResultEntity

@Database(entities = [LoveResultEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun calculatedResultDao(): LoveResultDao
}