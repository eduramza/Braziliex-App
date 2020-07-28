package com.eduramza.mybraziliexapp.data.repository.local

import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies

interface LocalRepository {

    suspend fun insertCrypto(cryptos: LocalCurrencies)
    suspend fun updatePrices(price: Double, coin: String)
    suspend fun listAllCryptos(): List<LocalCurrencies>
    suspend fun updateQtde(qtde: Double, name: String)
    suspend fun deleteAll()
}