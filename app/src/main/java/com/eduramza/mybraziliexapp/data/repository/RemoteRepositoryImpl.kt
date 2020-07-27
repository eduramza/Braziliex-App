package com.eduramza.mybraziliexapp.data.repository

import com.eduramza.mybraziliexapp.data.remote.BraziliexServiceApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class RemoteRepositoryImpl(
    private val api: BraziliexServiceApi
) : RemoteRepository{

    override suspend fun getAllTickers() = api.getTickets()

}