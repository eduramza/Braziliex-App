package com.eduramza.mybraziliexapp.data.repository.remote

import com.eduramza.mybraziliexapp.data.remote.BraziliexServiceApi
import com.eduramza.mybraziliexapp.data.repository.remote.RemoteRepository

class RemoteRepositoryImpl(
    private val api: BraziliexServiceApi
) : RemoteRepository {

    override suspend fun getAllTickers() = api.getTickets()

}