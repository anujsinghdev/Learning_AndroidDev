package com.example.jetpackapp5

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackapp5.ui.theme.JetpackApp5Theme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackApp5Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var textFieldValue = rememberSaveable { mutableStateOf("") }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                    {
                        TextFieldState(textFieldValue)
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldState(textFieldValue: MutableState<String>) {
    TextField(
        value = textFieldValue.value,
        onValueChange = {
            textFieldValue.value = it
        },
        label = { Text("Enter your name") }
    )
}
