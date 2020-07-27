package com.eduramza.mybraziliexapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.data.repository.RemoteRepository
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties

enum class MyStatus{LOADING, ERROR, DONE}

class MainViewModel(private val repository: RemoteRepository) : ViewModel() {

    private val _status = MutableLiveData<MyStatus>()
    val status: LiveData<MyStatus>
        get() = _status

    private val _data = MutableLiveData<List<Tickers.Coin>>()
    fun getData() = _data

    private val listCoins : MutableList<Tickers.Coin> = mutableListOf()

    init {
        getAllTickers()
    }

    private fun getAllTickers(){
        viewModelScope.launch {
            try{
                _status.value = MyStatus.LOADING
                val result = repository.getAllTickers()
                val properties = result::class.memberProperties

                properties.forEach { item ->
                    listCoins.add(item.getter.call(result) as Tickers.Coin)
                }

                _data.postValue( listCoins.filter { it.active == 1 } )

                _status.value = MyStatus.DONE

            } catch (e: Exception){
                _status.value = MyStatus.ERROR
            }
        }
    }
}