package com.eduramza.mybraziliexapp.data.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cryptos")
data class LocalCurrencies(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val coin_name : String,
    val coin_nickname: String,
    val qtde: Double = 0.0,
    val unit_price: Double = 0.0,
    val total: Double = 0.0
)