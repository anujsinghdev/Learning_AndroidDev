package com.example.jetpackapp3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackapp3.ui.theme.JetpackApp3Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackApp3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(color = Color(0xfffed523)),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    ) {
                        Text(
                            text = "Login",
                            fontSize = 36.sp,
                            fontFamily = FontFamily.SansSerif,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black,
                        )

                        Spacer(modifier = Modifier.height(130.dp))

                        TextField(
                            value = "", onValueChange = {},
                            placeholder = {
                                Text(text = "Enter your email")
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Black,
                                unfocusedIndicatorColor = Color.Black,
                                focusedTextColor = Color.DarkGray,
                                unfocusedTextColor = Color.Black,
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Person,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        TextField(
                            value = "", onValueChange = {},
                            placeholder = {
                                Text(text = "Enter your password")
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedIndicatorColor = Color.Black,
                                unfocusedIndicatorColor = Color.Black,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black
                            ),
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Rounded.Lock,
                                    contentDescription = null,
                                    tint = Color.Black
                                )
                            }
                        )

                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(checked = true, onCheckedChange = {})
                            Text(text = "I read and agree ")
                            Text(text = "Terms and Conditions", color = Color.Red)
                        }

                        Button(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 63.dp)
                                .clip(RoundedCornerShape(10.dp)),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Black,
                                contentColor = Color.White
                            ), shape = ButtonDefaults.elevatedShape
                        )
                        {
                            Text(text = "Login")
                        }
                    }
                }
            }
        }
    }
}

