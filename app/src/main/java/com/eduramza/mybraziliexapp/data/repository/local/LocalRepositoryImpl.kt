package com.eduramza.mybraziliexapp.data.repository.local

import com.eduramza.mybraziliexapp.data.database.CryptoDao
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies
import com.eduramza.mybraziliexapp.data.repository.local.LocalRepository

class LocalRepositoryImpl(private val dao: CryptoDao) :
    LocalRepository {

    override suspend fun insertCrypto(cryptos: LocalCurrencies) {
        dao.insertCryptos(cryptos)
    }

    override suspend fun updatePrices(price: Double, coin: String) {
        dao.updateCryptoPrices(price, coin)
    }

    override suspend fun listAllCryptos() = dao.getAllCryptos()

    override suspend fun updateQtde(qtde: Double, name: String) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        dao.deleteAll()
    }

}