package com.eduramza.mybraziliexapp.ui.balance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.local.LocalCurrencies
import com.eduramza.mybraziliexapp.data.repository.local.LocalRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class BalanceViewModel(private val localRepository: LocalRepository) : ViewModel() {

    private val _data = MutableLiveData<List<LocalCurrencies>>()
    fun getData(): LiveData<List<LocalCurrencies>> = _data

    private val _balance = MutableLiveData<Double>()
    fun getBalanceEstimated(): LiveData<Double> = _balance

    init {
        getAllCoins()
    }

    private fun getAllCoins(){
        viewModelScope.launch {
            try {
                val result = localRepository.listAllCryptos()

                _data.postValue(result.sortedBy { it.qtde }.reversed())
                updateBalance()
            } catch (e: Exception){
                //Do nothing yet
            }
        }
    }

    fun updateQtdeCoins(qtde: Double, id: Int){
        viewModelScope.launch {
            try {
                localRepository.updateQtde(qtde, id)
                updateBalance()
            } catch (e: Exception){
                //Do anything
            }
        }
    }

    private fun updateBalance(){
        viewModelScope.launch {
            val result = localRepository.listAllCryptos()
            val sum = result.sumByDouble { it.qtde * it.unit_price }
            _balance.postValue(sum)
        }
    }

}