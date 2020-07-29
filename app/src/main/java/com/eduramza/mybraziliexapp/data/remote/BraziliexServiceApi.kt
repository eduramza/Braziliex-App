package com.eduramza.mybraziliexapp.data.remote

import com.eduramza.mybraziliexapp.data.model.Tickers
import retrofit2.http.GET
import retrofit2.http.Query

interface BraziliexServiceApi {
    @GET("ticker")
    suspend fun getTickets(): Tickers

}