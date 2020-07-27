package com.eduramza.mybraziliexapp.data.remote

import com.eduramza.mybraziliexapp.data.model.Tickers
import retrofit2.http.GET

interface BraziliexServiceApi {
    @GET("ticker?active=1")
    suspend fun getTickets(): Tickers
}