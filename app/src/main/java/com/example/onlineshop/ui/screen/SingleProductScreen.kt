package com.example.onlineshop.ui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshop.ui.component.AppGradient
import com.example.onlineshop.ui.component.AppImage
import com.example.onlineshop.viewmodel.SingleProductViewModel

@Composable
fun SingleProductScreen(
    navController: NavHostController,
    id: Long,
    innerPadding: PaddingValues,
    singleProductViewModel: SingleProductViewModel = hiltViewModel()
) {
    LaunchedEffect(id) {
        singleProductViewModel.loadProduct(id = id)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AppImage(
            model = singleProductViewModel.product?.image ?: "",
            description = singleProductViewModel.product?.title ?: ""
        )
        AppGradient(modifier = Modifier
            .height(600.dp)
            .align(Alignment.BottomCenter))
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}