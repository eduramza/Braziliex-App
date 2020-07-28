package com.eduramza.mybraziliexapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies

@Dao
interface CryptoDao {
    @Insert
    suspend fun insertCryptos(cryptos: LocalCurrencies)

    @Query("SELECT * FROM cryptos")
    suspend fun getAllCryptos(): List<LocalCurrencies>

    @Query("UPDATE cryptos SET unit_price = :price WHERE coin_name = :coin")
    suspend fun updateCryptoPrices(price: Double, coin: String)

    @Query("UPDATE cryptos SET qtde = :qtde WHERE id = :id")
    suspend fun updateQtde(qtde: Double, id: Int)

    @Query("DELETE FROM CRYPTOS")
    suspend fun deleteAll()
}