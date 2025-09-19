package com.example.onlineshop.ui.component

import androidx.compose.foundation.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.example.onlineshop.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavBar(navController: NavHostController) {
    TopAppBar(
        title = {
            AnimatedSlideIn(300) {
                Image(
                    painter = painterResource(id = R.drawable.onlineshop),
                    contentDescription = ""
                )
            }
        }, actions = {
            AnimatedSlideIn(600) {
                IconButton(onClick = {}) {
                    BadgedBox(
                        badge = {
                            Badge {
                                Text("2")
                            }
                        }
                    ) {
                        Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Basket")
                    }
                }
            }
            AnimatedSlideIn(900) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Person, contentDescription = "Basket")
                }
            }

        }
    )
}