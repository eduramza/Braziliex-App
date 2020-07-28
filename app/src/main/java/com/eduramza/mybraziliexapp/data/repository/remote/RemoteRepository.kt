package com.eduramza.mybraziliexapp.data.repository.remote

import com.eduramza.mybraziliexapp.data.model.Tickers
import kotlinx.coroutines.Deferred
import retrofit2.Response

interface RemoteRepository {
    suspend fun getAllTickers(): Tickers
}