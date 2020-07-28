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

    private val listCoin : MutableList<LocalCurrencies> = mutableListOf()

    init {
        getAllCoins()
    }

    private fun getAllCoins(){
        viewModelScope.launch {
            try {
                val result = localRepository.listAllCryptos()

                _data.postValue(result)

            } catch (e: Exception){
                //Do nothing yet
            }
        }
    }

}