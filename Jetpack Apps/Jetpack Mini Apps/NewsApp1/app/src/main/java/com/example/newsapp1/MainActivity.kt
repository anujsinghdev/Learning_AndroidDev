package com.example.newsapp1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.newsapp1.ui.theme.NewsApp1Theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NewsApp1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GlobalScope.launch (Dispatchers.IO) {

                        Log.d("TAG" , "Data : ${RetrofitClient.client.ApiCall()}")

                    }
                }
            }
        }
    }
}
