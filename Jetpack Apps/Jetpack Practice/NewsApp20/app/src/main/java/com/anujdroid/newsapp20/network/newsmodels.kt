package com.anujdroid.newsapp20.network

data class newsmodels(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)