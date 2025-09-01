package com.anujdroid.newsapp20.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    // https://newsapi.org/v2/everything?q=tesla&from=2025-08-01&sortBy=publishedAt&apiKey=1e71bdddaef4466aaf1763bcfb5c2031

    @GET("everything")
    fun getTopHeadlines(
        @Query("tesla") tesla: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    )
}