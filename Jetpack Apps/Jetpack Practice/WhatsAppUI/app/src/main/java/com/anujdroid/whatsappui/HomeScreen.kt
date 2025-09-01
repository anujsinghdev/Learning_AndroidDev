package com.anujdroid.whatsappui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Update
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

// Dummy data class for a chat item
data class Chat(
    val name: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0
)

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    // The Scaffold is the main layout for a screen that provides slots for a top app bar,
    // a bottom app bar, a floating action button, and content.
    Scaffold(
        topBar = {
            // The TopAppBar provides the title and action buttons at the top of the screen.
            TopAppBar(
                title = {
                    Text(text = "MatesApp")
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                    navigationIconContentColor = MaterialTheme.colorScheme.primary,
                    actionIconContentColor = MaterialTheme.colorScheme.primary,
                    scrolledContainerColor = MaterialTheme.colorScheme.onSurface,
                ),
                actions = {
                    // This RowScope block contains the action buttons on the right.
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More options")
                    }
                }
            )
        },
        bottomBar = {
            // The NavigationBar is used to provide bottom navigation for three to five destinations.
            NavigationBar {
                // Here we define the items for our navigation bar.
                NavigationBarItem(
                    selected = true, // Highlight this as the currently selected item
                    onClick = { /*TODO: Navigate to Chats screen*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "Chats"
                        )
                    },
                    label = { Text("Chats") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO: Navigate to Updates screen*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Update,
                            contentDescription = "Updates"
                        )
                    },
                    label = { Text("Updates") }
                )
                NavigationBarItem(
                    selected = false,
                    onClick = { /*TODO: Navigate to Calls screen*/ },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Call,
                            contentDescription = "Calls"
                        )
                    },
                    label = { Text("Calls") }
                )
            }
        },
        floatingActionButton = {
            // The FloatingActionButton is a circular button that triggers the primary action on a screen.
            FloatingActionButton(onClick = { /*TODO: Handle action like starting a new chat*/ }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "New chat"
                )
            }
        }
    ) { innerPadding ->
        // The content of the screen goes here. The innerPadding is crucial to apply
        // to your content to prevent it from being obscured by the top and bottom bars.
        Box(modifier = Modifier.padding(innerPadding)) {
            // Generate some dummy chat data
            val dummyChats = listOf(
                Chat("Alice", "Hey, what's up?", "10:30 AM", 2),
                Chat("Bob", "I'll be there in 5.", "10:25 AM"),
                Chat("Charlie", "Did you see the new movie?", "Yesterday"),
                Chat("David", "Let's catch up soon!", "Sunday"),
                Chat("Eva", "Okay, sounds good.", "Friday"),
            )

            // LazyColumn is used for performance when displaying a large list of items.
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(dummyChats) { chat ->
                    ChatListItem(chat = chat)
                }
            }
        }
    }
}

// A custom composable for a single chat list item.
@Composable
fun ChatListItem(chat: Chat) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Profile picture placeholder
        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(Color.Gray),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = chat.name.first().toString(),
                style = MaterialTheme.typography.bodyLarge,
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.padding(horizontal = 8.dp))

        // Name and last message
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = chat.name,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = chat.lastMessage,
                style = MaterialTheme.typography.bodyMedium
            )
        }

        // Timestamp
        Text(
            text = chat.time,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
    }
}
