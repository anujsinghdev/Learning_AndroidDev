package com.example.newsapp1

import HomeUI
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.newsapp1.MyViewModel.MyViewModel

import com.example.newsapp1.ui.theme.NewsApp1Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel = MyViewModel()
            NewsApp1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    HomeUI(
                        viewModel
                    )
                }
            }
        }
    }
}
