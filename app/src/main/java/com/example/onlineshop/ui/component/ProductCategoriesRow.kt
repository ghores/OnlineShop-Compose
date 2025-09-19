package com.example.onlineshop.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.onlineshop.model.products.ProductCategory
import com.example.onlineshop.viewmodel.HomeViewModel

@Composable
fun ProductCategoriesRow(
    homeViewModel: HomeViewModel,
    navController: NavHostController
) {
    DataUiStateHandler(
        state = homeViewModel.productCategories,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { categories ->
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(categories) { index, item ->
                AnimatedSlideIn(index * 100) {
                    ProductCategoriesItem(productCategory = item, navController = navController)
                }
            }
        }
    }
}

@Composable
fun ProductCategoriesItem(productCategory: ProductCategory, navController: NavHostController) {
    AppCard(
        modifier = Modifier
            .width(160.dp)
            .height(200.dp),
        image = productCategory.image,
        title = productCategory.title,
    ) {
        navController.navigate("products/${productCategory.id}/${productCategory.title}")
    }
}