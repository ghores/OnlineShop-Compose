package com.example.onlineshop.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.onlineshop.ui.component.TopNavBar

@Composable
fun OnlineShopApp() {
    val navController = rememberNavController()
    val isFullScreen = checkForFullScreen(navController)
    Scaffold(
        topBar = { if (!isFullScreen) TopNavBar(navController = navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(if (!isFullScreen) innerPadding else PaddingValues(0.dp))
        ) {
            NavHost(navController, startDestination = "home") {
                composable(route = "home") {
                    HomeScreen(navController)
                }
                composable(
                    route = "products/{catId}/{title}",
                    arguments = listOf(
                        navArgument("catId") { type = NavType.LongType },
                        navArgument("title") { type = NavType.StringType }
                    )
                ) {
                    val catId = it.arguments?.getLong("catId") ?: 0
                    val title = it.arguments?.getString("title") ?: ""
                    ProductsScreen(navController, catId, title)
                }
                composable(
                    route = "showProduct/{id}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.LongType }
                    )
                ) {
                    val id = it.arguments?.getLong("id") ?: 0
                    SingleProductScreen(navController, id, innerPadding)
                }
            }
        }
    }
}

@Composable
fun checkForFullScreen(navController: NavHostController): Boolean {
    val fullScreenRoutes = listOf("login", "showProduct")
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route ?: ""
    return fullScreenRoutes.any {
        currentRoute.startsWith(it)
    }
}