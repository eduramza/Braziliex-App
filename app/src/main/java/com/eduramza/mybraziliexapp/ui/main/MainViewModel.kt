package com.eduramza.mybraziliexapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eduramza.mybraziliexapp.data.model.Tickers
import com.eduramza.mybraziliexapp.data.repository.RemoteRepository
import kotlinx.coroutines.launch

enum class MyStatus{LOADING, ERROR, DONE}

class MainViewModel(private val repository: RemoteRepository) : ViewModel() {

    private val _status = MutableLiveData<MyStatus>()
    val status: LiveData<MyStatus>
        get() = _status

    private val _data = MutableLiveData<Tickers>()
    val data: LiveData<Tickers>
        get() = _data

    fun getAllTickers(){
        viewModelScope.launch {
            try{
                _status.value = MyStatus.LOADING
                _data.postValue(repository.getAllTickers().await())

                _status.value = MyStatus.DONE

            } catch (e: Exception){
                _status.value = MyStatus.ERROR
            }
        }
    }
}