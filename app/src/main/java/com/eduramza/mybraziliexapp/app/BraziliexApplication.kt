package com.eduramza.mybraziliexapp.app

import android.app.Application
import com.eduramza.mybraziliexapp.app.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BraziliexApplication: Application(){

    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidLogger()
            androidContext(this@BraziliexApplication)
            modules(appModule)
        }
    }
}