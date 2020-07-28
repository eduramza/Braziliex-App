package com.eduramza.mybraziliexapp.app.di

import com.eduramza.mybraziliexapp.data.database.AppDatabase
import com.eduramza.mybraziliexapp.data.remote.RemoteServiceRetrofit
import com.eduramza.mybraziliexapp.data.repository.local.LocalRepository
import com.eduramza.mybraziliexapp.data.repository.local.LocalRepositoryImpl
import com.eduramza.mybraziliexapp.data.repository.remote.RemoteRepository
import com.eduramza.mybraziliexapp.data.repository.remote.RemoteRepositoryImpl
import com.eduramza.mybraziliexapp.ui.balance.BalanceViewModel
import com.eduramza.mybraziliexapp.ui.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RemoteServiceRetrofit.createRetrofitInterface() }
    single<RemoteRepository> {
        RemoteRepositoryImpl(
            get()
        )
    }
    viewModel{ MainViewModel(get(), get()) }
    viewModel { BalanceViewModel(get()) }
}

val localModule = module {
//    single { Room.databaseBuilder(get(),
//        AppDatabase::class.java,
//        "Braziliex-Database")
//        .build() }
    single { AppDatabase.invoke(get()) }
    single { get<AppDatabase>().cryptoDao() }
    single<LocalRepository> {
        LocalRepositoryImpl(
            get()
        )
    }

}