package com.anujdroid.contactapp.ui.screen.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anujdroid.contactapp.ui.screen.AddContact
import com.anujdroid.contactapp.ui.screen.HomeScreen

@Composable
fun NavApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home",  // ✅ Use string instead
        modifier = Modifier
    ) {
        composable("home") {  // ✅ String route
            HomeScreen(navController = navController)
        }
        composable("add_contact") {  // ✅ String route
            AddContact(navController = navController)
        }
    }
}
