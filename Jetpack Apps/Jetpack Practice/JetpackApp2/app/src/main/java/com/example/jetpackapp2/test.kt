package com.example.jetpackapp2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class Contact(
    val name: String,
    val phoneNumber: String,
)

@Composable
fun Test() {
    val contacts = remember {
        listOf(
            Contact("John Doe", "123-456-789"),
            Contact("Jane Smith", "987-654-321"),
            Contact("Alice Johnson", "555-222-111"),
            Contact("Bob Brown", "444-333-222"),
            Contact("Charlie Black", "111-999-888"),
            Contact("John Doe", "123-456-789"),
            Contact("Jane Smith", "987-654-321"),
            Contact("Alice Johnson", "555-222-111"),
            Contact("Bob Brown", "444-333-222"),
            Contact("Charlie Black", "111-999-888"),
            Contact("John Doe", "123-456-789"),
            Contact("Jane Smith", "987-654-321"),
            Contact("Alice Johnson", "555-222-111"),
            Contact("Bob Brown", "444-333-222"),
            Contact("Charlie Black", "111-999-888")
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(contacts) { contact ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column {
                    Text(text = contact.name)
                    Text(text = contact.phoneNumber)
                }
            }
        }
    }
}
