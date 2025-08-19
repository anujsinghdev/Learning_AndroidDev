package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme


val ModernFontFamily = FontFamily.SansSerif
val DisplayFontFamily = FontFamily.Serif

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NavigationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavigationApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun NavigationApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "screen_a",
        modifier = modifier
    ) {
        composable("screen_a") {
            ScreenA(navController = navController)
        }
        composable("screen_b") {
            ScreenB(navController = navController)
        }
    }
}

@Composable
fun ScreenA(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // Gradient background
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF667eea), // Light blue
                        Color(0xFF764ba2)  // Purple
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                // First line: "Screen A"
                append("Screen A\n")
                addStyle(
                    SpanStyle(
                        color = Color.Yellow,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.SansSerif
                    ),
                    start = 0,
                    end = "Screen A".length
                )
                // Second line: "Click to go to Screen B"
                append("Click to go to Screen B")
                addStyle(
                    SpanStyle(
                        color = Color.Cyan,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = FontFamily.Serif
                    ),
                    start = "Screen A\n".length,
                    end = ("Screen A\nClick to go to Screen B").length
                )
            },
            modifier = Modifier
                .padding(32.dp)
                .clickable { navController.navigate("screen_b") },
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )
    }
}

@Composable
fun ScreenB(navController: NavController, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                // Different gradient background
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFf093fb), // Pink
                        Color(0xFFf5576c)  // Red
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = buildAnnotatedString {
                // First line: "Screen B"
                append("Screen B\n")
                addStyle(
                    SpanStyle(
                        color = Color.Yellow,
                        fontSize = 32.sp,              
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = ModernFontFamily
                    ),
                    start = 0,
                    end = "Screen B".length
                )

                append("Click to go back to Screen A")
                addStyle(
                    SpanStyle(
                        color = Color(0xFFE1F5FE),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = ModernFontFamily
                    ),
                    start = "Screen B\n".length,
                    end = ("Screen B\nClick to go back to Screen A").length
                )
            },
            modifier = Modifier
                .padding(32.dp)
                .clickable {
                    navController.navigate("screen_a")
                },
            textAlign = TextAlign.Center,
            lineHeight = 36.sp
        )
    }
}
