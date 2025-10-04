package com.example.onlineshop.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.onlineshop.model.db.BasketEntity
import com.example.onlineshop.ui.component.animation.AnimatedSlideIn
import com.example.onlineshop.ui.component.dialog.AppDialog
import com.example.onlineshop.ui.component.app.AppImage
import com.example.onlineshop.ui.component.app.PriceText
import com.example.onlineshop.ui.theme.AppGreen
import com.example.onlineshop.viewmodel.BasketViewModel

@Composable
fun BasketScreen(
    navController: NavHostController,
    basketViewModel: BasketViewModel = hiltViewModel()
) {
    val basketItem by basketViewModel.basketItems.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var itemToDelete by remember { mutableStateOf<BasketEntity?>(null) }
    val totalPrice = basketItem.sumOf { it.price?.times(it.quantity) ?: 0 }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Cart",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(Modifier.height(10.dp))
        if (basketItem.isEmpty()) {
            Text(text = "Your cart is empty")
        } else {
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(basketItem) { index, item ->
                    AnimatedSlideIn(index * 200) {
                        Column {
                            BasketItemRow(
                                basketEntity = item,
                                onIncrease = { basketViewModel.increaseQuantity(item) },
                                onDecrease = { basketViewModel.decreaseQuantity(item) },
                                onDelete = {
                                    showDialog = true
                                    itemToDelete = item
                                }
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                AnimatedSlideIn {
                    Text(text = "Total Price: ", fontWeight = FontWeight.Bold)
                }
                AnimatedSlideIn {
                    PriceText(
                        totalPrice,
                        priceColor = Color.Black,
                        priceSize = 16.sp,
                        tomanColor = Color.Gray,
                        tomanSize = 12.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(25.dp))
            Row(modifier = Modifier.fillMaxWidth()) {
                AnimatedSlideIn {
                    Button(
                        onClick = {
                            navController.navigate("home")
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.White,
                            contentColor = Color.Black
                        )
                    ) {
                        Text(text = "Continue shopping")
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                AnimatedSlideIn {
                    Button(
                        onClick = {
                            navController.navigate("userPayment")
                        },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = AppGreen
                        )
                    ) {
                        Text(text = "Proceed to payment")
                    }
                }
            }
        }
    }
    AppDialog(
        showDialog = showDialog,
        onDismiss = { showDialog = false },
        onConfirm = {
            itemToDelete?.let { basketViewModel.deleteItem(it) }
            showDialog = false
        },
        onCancel = { showDialog = false }
    )
}

@Composable
fun BasketItemRow(
    basketEntity: BasketEntity,
    onIncrease: (BasketEntity) -> Unit = {},
    onDecrease: (BasketEntity) -> Unit = {},
    onDelete: (BasketEntity) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Card(modifier = Modifier.size(60.dp)) {
                AppImage(
                    model = basketEntity.image ?: "",
                    description = basketEntity.title ?: ""
                )
            }
            Column {
                Text(
                    text = basketEntity.title ?: "",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                PriceText(
                    price = ((basketEntity.price ?: 0) * basketEntity.quantity),
                    priceColor = Color.Black,
                    priceSize = 16.sp,
                    tomanColor = Color.Gray,
                    tomanSize = 11.sp
                )
            }
            Spacer(Modifier.weight(1f))
            Column {
                Text(
                    text = "Size: ${basketEntity.size}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp
                )
                Card(
                    shape = CircleShape,
                    colors = CardDefaults.cardColors(
                        containerColor = Color("#${basketEntity.colorHex}".toColorInt())
                    ),
                    modifier = Modifier
                        .height(25.dp)
                        .width(45.dp)
                ) { }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {
                    onDecrease(basketEntity)
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Decrease"
                    )
                }
                Text(text = basketEntity.quantity.toString(), modifier = Modifier.padding(8.dp))
                IconButton(onClick = {
                    onIncrease(basketEntity)
                }) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = "Increase"
                    )
                }
            }
            IconButton(onClick = {
                onDelete(basketEntity)
            }) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "Delete",
                    tint = Color.Red
                )
            }
        }
    }
}
