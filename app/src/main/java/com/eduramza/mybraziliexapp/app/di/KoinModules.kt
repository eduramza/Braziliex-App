package com.eduramza.mybraziliexapp.app.di

import com.eduramza.mybraziliexapp.data.remote.RemoteServiceRetrofit
import com.eduramza.mybraziliexapp.data.repository.RemoteRepository
import com.eduramza.mybraziliexapp.data.repository.RemoteRepositoryImpl
import com.eduramza.mybraziliexapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RemoteServiceRetrofit.createRetrofitInterface() }
    single<RemoteRepository> { RemoteRepositoryImpl(get()) }
    viewModel{ MainViewModel(get()) }
}