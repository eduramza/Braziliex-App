package com.eduramza.mybraziliexapp.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies
import com.eduramza.mybraziliexapp.data.repository.local.LocalRepository
import com.eduramza.mybraziliexapp.data.repository.remote.RemoteRepository
import com.eduramza.mybraziliexapp.extensions.getCryptoName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.reflect.full.memberProperties

enum class MyStatus{LOADING, ERROR, DONE}

class MainViewModel(private val remoteRepository: RemoteRepository,
                    private val localRepository: LocalRepository) : ViewModel() {

    private val _status = MutableLiveData<MyStatus>()
    val status: LiveData<MyStatus>
        get() = _status

    private val _data = MutableLiveData<List<Tickers.Coin>>()
    fun getData(): LiveData<List<Tickers.Coin>> = _data

    private val listCoins : MutableList<Tickers.Coin> = mutableListOf()

    init {
        getAllTickers()
    }

    private fun getAllTickers(){
        viewModelScope.launch {
            try{
                _status.value = MyStatus.LOADING
                val result = remoteRepository.getAllTickers()
                val properties = result::class.memberProperties
                val tempList = mutableListOf<Tickers.Coin>()

                properties.forEach { item ->
                    tempList.add(item.getter.call(result) as Tickers.Coin)
                }

                listCoins.addAll(tempList.filter { it.active == 1 })

                _data.postValue(
                    listCoins.sortedBy { it.quoteVolume24 }
                            .reversed() )

                _status.value = MyStatus.DONE

            } catch (e: Exception){
                _status.value = MyStatus.ERROR
            }
        }
    }

    fun updateLocalDatabase(){
        viewModelScope.launch {
            val localList = localRepository.listAllCryptos()

            if (localList.isEmpty()){
                insertFirstTime()
            } else {
                updatePrices()
            }
        }
    }

    private suspend fun insertFirstTime() =
        withContext(Dispatchers.IO) {
            listCoins.forEach {
                async {
                    val coin = LocalCurrencies(
                        coin_name = it.market.getCryptoName(),
                        coin_nickname = it.market.split("_")[0].toUpperCase(),
                        unit_price = it.last
                    )
                    localRepository.insertCrypto(coin)
                }
            }
        }

    private suspend fun updatePrices() = withContext(Dispatchers.IO){
        listCoins.forEach {
            async {
                Log.d("updatePrice", "new price: ${it.last} for ${it.market.getCryptoName()}")
                localRepository.updatePrices(it.last, it.market.getCryptoName())
            }
        }
    }
}