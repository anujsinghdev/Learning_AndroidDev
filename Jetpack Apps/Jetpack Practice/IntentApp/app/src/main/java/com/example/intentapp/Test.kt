package com.example.intentapp

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri

@Composable
fun Test(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val intent = Intent(
        Intent.ACTION_VIEW,
        "https://www.google.com".toUri()
    )

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("This is Intent App")

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                context.startActivity(intent)
            },
            modifier = Modifier.height(50.dp)
        ) {
            Text(text = "Click Me To Open ")
        }
    }

}