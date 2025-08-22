package com.example.newsapp1

import com.example.newsapp1.models.NewsModels
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{


    @GET("top-headlines")
    suspend fun ApiCall(
        @Query("country") country: String = "us",
        @Query("category") category: String = "business",
        @Query("apiKey") apiKey: String = API_KEY
    ) : Response<NewsModels>
}