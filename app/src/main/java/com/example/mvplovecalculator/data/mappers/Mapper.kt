package com.example.mvplovecalculator.data.mappers

import com.example.mvplovecalculator.data.model.CalculatedResult
import com.example.mvplovecalculator.data.database.entities.LoveResultEntity

fun CalculatedResult.toEntity(): LoveResultEntity {
    return LoveResultEntity(
        firstName = this.firstName,
        secondName = this.secondName,
        percentage = this.percentage,
        result = this.result
    )
}