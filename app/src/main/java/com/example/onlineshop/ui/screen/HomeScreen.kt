package com.example.onlineshop.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshop.ui.component.ProductCategoriesRow
import com.example.onlineshop.ui.component.ProductsView
import com.example.onlineshop.ui.component.SliderRow
import com.example.onlineshop.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .padding(10.dp)
            .verticalScroll(rememberScrollState())
    ) {
        SliderRow(homeViewModel)
        Spacer(Modifier.height(10.dp))
        ProductCategoriesRow(homeViewModel)
        Spacer(Modifier.height(10.dp))
        ProductsView(homeViewModel)
    }
}