package com.example.newsapp1.network

import com.example.newsapp1.utlis.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiProvider {

    val okhttpClient = OkHttpClient.Builder()

    fun providerApi() =
        Retrofit.Builder().baseUrl(BASE_URL).client(okhttpClient.build()).addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)

}