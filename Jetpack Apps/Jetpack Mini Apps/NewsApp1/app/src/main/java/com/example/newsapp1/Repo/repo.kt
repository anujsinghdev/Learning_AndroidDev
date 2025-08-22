package com.example.newsapp1.Repo

import com.example.newsapp1.models.NewsModels
import com.example.newsapp1.network.ApiProvider
import retrofit2.Response

class Repo {
    suspend fun newsProvider(
        country: String,
        category: String,
    ): Response<NewsModels> {
        return ApiProvider.providerApi().ApiCall(
            country = country,
            category = category
        )
    }
}
