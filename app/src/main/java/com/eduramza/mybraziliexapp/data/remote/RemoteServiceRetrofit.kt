package com.eduramza.mybraziliexapp.data.remote

import com.eduramza.mybraziliexapp.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_PUBLIC_URL = "https://braziliex.com/api/v1/public/"

class RemoteServiceRetrofit {
    companion object{
        fun createRetrofitInterface(): BraziliexServiceApi{
            val httpClient = OkHttpClient.Builder()

            if (BuildConfig.DEBUG){
                httpClient.addInterceptor(LoggingInterceptor())
            }

            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_PUBLIC_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

            return retrofit.create(BraziliexServiceApi::class.java)
        }
    }

}