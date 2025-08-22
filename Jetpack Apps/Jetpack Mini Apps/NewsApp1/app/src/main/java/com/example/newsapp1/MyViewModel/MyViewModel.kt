package com.example.newsapp1.MyViewModel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp1.Repo.Repo
import com.example.newsapp1.models.NewsModels
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel : ViewModel() {

    val response = mutableStateOf<NewsModels?>(null)

    val repo = Repo()

    init {
        feachNews()
    }

    fun feachNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = repo.newsProvider(
                country = "us",
                category = "business"
            )
            response.value = data.body()
            Log.d("Tag","fetchNews: ${data}")
        }

    }

}