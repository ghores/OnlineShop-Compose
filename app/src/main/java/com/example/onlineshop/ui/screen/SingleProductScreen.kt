package com.example.onlineshop.ui.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshop.ui.component.animation.AnimatedSlideIn
import com.example.onlineshop.ui.component.graphic.AppGradient
import com.example.onlineshop.ui.component.app.AppImage
import com.example.onlineshop.ui.component.app.PriceText
import com.example.onlineshop.viewmodel.BasketViewModel
import com.example.onlineshop.viewmodel.SingleProductViewModel

@Composable
fun SingleProductScreen(
    navController: NavHostController,
    id: Long,
    innerPadding: PaddingValues,
    singleProductViewModel: SingleProductViewModel = hiltViewModel(),
    basketViewModel: BasketViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(id) {
        singleProductViewModel.loadProduct(id = id)
    }
    Box(modifier = Modifier.fillMaxSize()) {
        AppImage(
            model = singleProductViewModel.product?.image ?: "",
            description = singleProductViewModel.product?.title ?: ""
        )
        AppGradient(
            modifier = Modifier
                .height(800.dp)
                .align(Alignment.BottomCenter)
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.TopStart)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = ""
                )
            }
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(20.dp)
            ) {
                AnimatedSlideIn {
                    Text(
                        text = singleProductViewModel.product?.title ?: "",
                        color = Color.White,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(10.dp))
                }
                AnimatedSlideIn(delay = 200) {
                    PriceText(price = singleProductViewModel.product?.price ?: 0)
                }
                Spacer(Modifier.height(25.dp))
                ProductSizesRow(singleProductViewModel)
                Spacer(Modifier.height(25.dp))
                ProductColorsRow(singleProductViewModel)
                Spacer(Modifier.height(25.dp))
                AnimatedSlideIn(1800, durationMillis = 3000) {
                    Text(
                        singleProductViewModel.product?.description ?: "",
                        color = Color.LightGray,
                        fontSize = 16.sp
                    )
                }
                Spacer(Modifier.height(30.dp))
                AnimatedSlideIn(3000) {
                    Button(
                        onClick = {
                            basketViewModel.addToBasket(
                                singleProductViewModel.product,
                                singleProductViewModel.selectedColor,
                                singleProductViewModel.selectedSize
                            )
                            Toast.makeText(context, "Product added to basket", Toast.LENGTH_SHORT)
                                .show()
                            navController.popBackStack()

                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(
                            "Buy Now",
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Composable
fun ProductSizesRow(singleProductViewModel: SingleProductViewModel) {
    AnimatedSlideIn(delay = 400) {
        Text(
            text = "Size",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(Modifier.height(10.dp))
    LazyRow {
        itemsIndexed(singleProductViewModel.product?.sizes ?: listOf()) { index, item ->
            AnimatedSlideIn(delay = 600 + (index * 200)) {
                TextButton(
                    onClick = {
                        singleProductViewModel.selectedSize = item
                    },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (singleProductViewModel.selectedSize == item) Color.White else Color.Transparent,
                        contentColor = if (singleProductViewModel.selectedSize == item) Color.Black else Color.White
                    )
                ) {
                    Text(item.title ?: "", fontWeight = FontWeight.Bold)
                }
            }
            Spacer(Modifier.width(5.dp))
        }
    }
}

@Composable
fun ProductColorsRow(singleProductViewModel: SingleProductViewModel) {
    AnimatedSlideIn(delay = 1000) {
        Text(
            text = "Color",
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(Modifier.height(10.dp))
    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
        itemsIndexed(singleProductViewModel.product?.colors ?: listOf()) { index, item ->
            AnimatedSlideIn(delay = 1200 + (index * 200)) {
                TextButton(
                    onClick = {
                        singleProductViewModel.selectedColor = item
                    },
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color("#${item.hexValue}".toColorInt())
                    ),
                    modifier = Modifier.size(40.dp),
                    border = BorderStroke(
                        2.dp,
                        if (singleProductViewModel.selectedColor == item) Color.Red else Color.White
                    )
                ) {
                    if (singleProductViewModel.selectedColor == item) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}