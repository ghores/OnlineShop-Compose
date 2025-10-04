package com.example.onlineshop.ui.component.app

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.onlineshop.ui.component.animation.AnimatedSlideIn
import com.example.onlineshop.ui.component.basic.DataUiStateHandler
import com.example.onlineshop.viewmodel.HomeViewModel

@Composable
fun ProductsView(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    Column {
        ProductFilterRow(homeViewModel)
        Spacer(Modifier.height(10.dp))
        ProductListView(homeViewModel, navController)
    }
}

@Composable
fun ProductFilterRow(homeViewModel: HomeViewModel) {
    val filters = listOf("All", "New", "Popular")
    var selectedIndex by remember { mutableIntStateOf(0) }
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(filters) { index, item ->
            TextButton(
                onClick = {
                    selectedIndex = index
                    when (selectedIndex) {
                        0 -> homeViewModel.loadAllProducts()
                        1 -> homeViewModel.loadNewProducts()
                        2 -> homeViewModel.loadPopularProducts()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedIndex == index) Color.LightGray else Color.Transparent,
                    contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black
                ),
                modifier = Modifier.width(90.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = item,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }
    }
}

@Composable
fun ProductListView(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    DataUiStateHandler(
        state = homeViewModel.products,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { products ->
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            for ((index, item) in products.withIndex()) {
                AnimatedSlideIn(index * 100) {
                    AppCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        image = item.image,
                        title = item.title,
                        onClick = {
                            navController.navigate("showProduct/${item.id}")
                        }
                    )
                }
            }
        }
    }
}