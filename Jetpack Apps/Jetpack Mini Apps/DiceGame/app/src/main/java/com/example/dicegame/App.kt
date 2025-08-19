package com.example.dicegame

import android.R.attr.fontWeight
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Preview(showBackground = true)
@Composable
fun App(modifier: Modifier = Modifier, innerPadding: PaddingValues = PaddingValues(0.dp)) {

    var scorePlayer1 = remember { mutableIntStateOf(0) }
    var scorePlayer2 = remember { mutableIntStateOf(0) }
    var isPlayer1Turn = remember { mutableStateOf(true) }

    val images = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )

    // Store the dice value (1-6) instead of the resource ID
    val currentDiceValue = remember { mutableIntStateOf(1) }

    // Check if game is over
    val gameOver = scorePlayer1.intValue >= 20 || scorePlayer2.intValue >= 20

    if (gameOver) {
        // WIN SCREEN - Only show this when game is over
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = if (scorePlayer1.intValue > scorePlayer2.intValue)
                        "🎉 Player 1 Wins! 🎉"
                    else
                        "🎉 Player 2 Wins! 🎉",
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xff4caf50)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Final Scores:")
                Text(text = "Player 1: ${scorePlayer1.intValue}")
                Text(text = "Player 2: ${scorePlayer2.intValue}")

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        // Reset game
                        scorePlayer1.intValue = 0
                        scorePlayer2.intValue = 0
                        isPlayer1Turn.value = true
                        currentDiceValue.intValue = 1
                    }
                ) {
                    Text(text = "Play Again")
                }
            }
        }
    } else {
        // GAME SCREEN - Only show this when game is ongoing
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (isPlayer1Turn.value) {
                Text(text = "Player 1 Turn",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2196F3)
                )
            } else {
                Text(text = "Player 2 Turn",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFF5722)

                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Use the dice value to get the correct image from the list
            Image(
                painter = painterResource(images[currentDiceValue.intValue - 1]),
                contentDescription = "Dice showing ${currentDiceValue.intValue}"
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Player 1 Score: ${scorePlayer1.intValue}")
            Text(text = "Player 2 Score: ${scorePlayer2.intValue}")

            Spacer(modifier = Modifier.height(16.dp))

            Row {
                Button(
                    onClick = {
                        val randomNumber = Random.nextInt(1, 7)
                        currentDiceValue.intValue = randomNumber
                        scorePlayer1.intValue += randomNumber
                        isPlayer1Turn.value = false
                    },
                    enabled = isPlayer1Turn.value,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2196F3), // Blue background
                        contentColor = Color.White,         // White text
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White
                    )
                ) {
                    Text(text = "Player 1 Roll",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }

                Spacer(modifier = Modifier.padding(horizontal = 8.dp))

                Button(
                    onClick = {
                        val randomNumber = Random.nextInt(1, 7)
                        currentDiceValue.intValue = randomNumber
                        scorePlayer2.intValue += randomNumber
                        isPlayer1Turn.value = true
                    },
                    enabled = !isPlayer1Turn.value,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFF5722),
                        contentColor = Color.White,
                        disabledContainerColor = Color.Gray,
                        disabledContentColor = Color.White)
                ) {
                    Text(text = "Player 2 Roll",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
