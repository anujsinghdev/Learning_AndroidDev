package com.example.newsapp1.MyViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.newsapp1.Repo.Repo
import com.example.newsapp1.models.NewsModels

class MyViewModel : ViewModel() {

    val response = mutableStateOf<NewsModels?>(null)

    suspend fun getNews(
        repo : Repo,
        country: String,
        category: String
    ) : NewsModels? {

        return repo.newsProvider(country, category).body()
    }

}