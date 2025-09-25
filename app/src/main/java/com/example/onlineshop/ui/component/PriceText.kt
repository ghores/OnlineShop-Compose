package com.example.onlineshop.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshop.ui.utils.formatPrice

@Composable
fun PriceText(
    price: Long,
    priceColor: Color = Color.White,
    priceSize: TextUnit = 26.sp,
    tomanColor: Color = Color.LightGray,
    tomanSize: TextUnit = 16.sp
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = formatPrice(price),
            color = priceColor,
            fontSize = priceSize
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = "Toman",
            color = tomanColor,
            fontSize = tomanSize
        )
    }
}