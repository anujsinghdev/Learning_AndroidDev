package com.example.shoplistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

data class ShoppingItem(
    val id: Int,
    val name: String,
    val quantity: Int,
    val isEditing: Boolean = false,
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingListUI() {
    // Initialize with sample data
    val shoppingItems = remember {
        mutableStateListOf<ShoppingItem>(
            ShoppingItem(1, "Apples", 5),
            ShoppingItem(2, "Bread", 2),
            ShoppingItem(3, "Milk", 1),
            ShoppingItem(4, "Chicken", 3),
            ShoppingItem(5, "Rice", 2),
            ShoppingItem(6, "Tomatoes", 8),
            ShoppingItem(7, "Cheese", 1),
            ShoppingItem(8, "Eggs", 12)
        )
    }
    var newItemName by remember { mutableStateOf("") }
    var newItemQuantity by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }
    var editingItem by remember { mutableStateOf<ShoppingItem?>(null) }
    var editItemName by remember { mutableStateOf("") }
    var editItemQuantity by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Shopping List") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog = true
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Item")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(shoppingItems) { item ->
                ShoppingItemCard(
                    item = item,
                    onEdit = {
                        editingItem = item
                        editItemName = item.name
                        editItemQuantity = item.quantity.toString()
                    },
                    onDelete = {
                        shoppingItems.remove(item)
                    }
                )
            }
        }

        // Add Item Dialog
        if (showDialog) {
            AlertDialog(
                onDismissRequest = {
                    showDialog = false
                    newItemName = ""
                    newItemQuantity = ""
                },
                title = { Text("Add New Item") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = newItemName,
                            onValueChange = { newItemName = it },
                            label = { Text("Item Name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = newItemQuantity,
                            onValueChange = { newItemQuantity = it },
                            label = { Text("Quantity") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (newItemName.isNotBlank()) {
                                shoppingItems.add(
                                    ShoppingItem(
                                        id = (shoppingItems.maxOfOrNull { it.id } ?: 0) + 1,
                                        name = newItemName,
                                        quantity = newItemQuantity.toIntOrNull() ?: 1
                                    )
                                )
                                newItemName = ""
                                newItemQuantity = ""
                                showDialog = false
                            }
                        }
                    ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showDialog = false
                            newItemName = ""
                            newItemQuantity = ""
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }

        // Edit Item Dialog
        editingItem?.let { item ->
            AlertDialog(
                onDismissRequest = {
                    editingItem = null
                    editItemName = ""
                    editItemQuantity = ""
                },
                title = { Text("Edit Item") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = editItemName,
                            onValueChange = { editItemName = it },
                            label = { Text("Item Name") },
                            modifier = Modifier.fillMaxWidth()
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        OutlinedTextField(
                            value = editItemQuantity,
                            onValueChange = { editItemQuantity = it },
                            label = { Text("Quantity") },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (editItemName.isNotBlank()) {
                                val index = shoppingItems.indexOf(item)
                                if (index != -1) {
                                    shoppingItems[index] = item.copy(
                                        name = editItemName,
                                        quantity = editItemQuantity.toIntOrNull() ?: 1
                                    )
                                }
                                editingItem = null
                                editItemName = ""
                                editItemQuantity = ""
                            }
                        }
                    ) {
                        Text("Save")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            editingItem = null
                            editItemName = ""
                            editItemQuantity = ""
                        }
                    ) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingItemCard(
    item: ShoppingItem,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = "Quantity: ${item.quantity}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Row {
                IconButton(onClick = onEdit) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit Item",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Item",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
