package org.example.kmpdicegame

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kmpdicegame.composeapp.generated.resources.Res
import kmpdicegame.composeapp.generated.resources.compose_multiplatform
import kmpdicegame.composeapp.generated.resources.dice_1
import kmpdicegame.composeapp.generated.resources.dice_2
import kmpdicegame.composeapp.generated.resources.dice_3
import kmpdicegame.composeapp.generated.resources.dice_4
import kmpdicegame.composeapp.generated.resources.dice_5
import kmpdicegame.composeapp.generated.resources.dice_6
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    // GAME CONFIG: Score needed to win
    val winningScore = 23

    // Game state
    val isPlayer1 = remember { mutableStateOf(true) }
    // Store scores as a list of two integers [Player1Score, Player2Score]
    val playerScores = remember { mutableStateListOf(0, 0) }
    val diceImages = listOf(
        Res.drawable.dice_1,
        Res.drawable.dice_2,
        Res.drawable.dice_3,
        Res.drawable.dice_4,
        Res.drawable.dice_5,
        Res.drawable.dice_6
    )
    val currentDiceImage = remember { mutableStateOf(Res.drawable.compose_multiplatform) }

    // Winner detection
    val winner = remember(playerScores[0], playerScores[1]) { // Depend on individual scores
        when {
            playerScores[0] >= winningScore -> 1
            playerScores[1] >= winningScore -> 2
            else -> null
        }
    }

    MaterialTheme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            contentAlignment = Alignment.Center
        ) {
            // Winner screen
            if (winner != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(40.dp)
                        .background(
                            MaterialTheme.colorScheme.primaryContainer,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(40.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "🎉 Player $winner Wins! 🎉",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer,
                        textAlign = TextAlign.Center
                    )
                    Spacer(Modifier.height(18.dp))
                    Button(
                        onClick = {
                            playerScores[0] = 0 // Reset Player 1 score
                            playerScores[1] = 0 // Reset Player 2 score
                            isPlayer1.value = true
                            currentDiceImage.value = Res.drawable.compose_multiplatform
                        },
                        shape = MaterialTheme.shapes.extraLarge
                    ) {
                        Text(text = "Play Again", fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    }
                }
            } else {
                // Main game UI
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 20.dp, vertical = 100.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Welcome to iDice Game By Anuj",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(Modifier.height(30.dp))
                    Image(
                        painter = painterResource(currentDiceImage.value),
                        contentDescription = null,
                        modifier = Modifier.size(220.dp)
                    )
                    Spacer(Modifier.height(30.dp))
                    OutlinedButton(
                        onClick = {
                            val randomNumber = (1..6).random()
                            currentDiceImage.value = diceImages[randomNumber - 1]
                            if (isPlayer1.value) {
                                playerScores[0] += randomNumber // Update Player 1 score
                            } else {
                                playerScores[1] += randomNumber // Update Player 2 score
                            }
                            isPlayer1.value = !isPlayer1.value
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(
                            text = if (isPlayer1.value) "Player 1's Turn - Roll Dice" else "Player 2's Turn - Roll Dice",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(Modifier.height(45.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Card(
                            modifier = Modifier.weight(1f).padding(end = 8.dp),
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Text(
                                text = "Player 1\n${playerScores[0]}", // Display Player 1 score
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                        Card(
                            modifier = Modifier.weight(1f).padding(start = 8.dp),
                            shape = MaterialTheme.shapes.medium,
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer)
                        ) {
                            Text(
                                text = "Player 2\n${playerScores[1]}", // Display Player 2 score
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                textAlign = TextAlign.Center,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSecondaryContainer
                            )
                        }
                    }
                    Spacer(Modifier.height(40.dp))
                    // Use emoji for cross-platform "refresh" icon
                    IconButton(
                        onClick = {
                            playerScores[0] = 0 // Reset Player 1 score
                            playerScores[1] = 0 // Reset Player 2 score
                            isPlayer1.value = true
                            currentDiceImage.value = Res.drawable.compose_multiplatform
                        },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text(text = "🔄", fontSize = 32.sp)
                    }
                }
            }
        }
    }
}
