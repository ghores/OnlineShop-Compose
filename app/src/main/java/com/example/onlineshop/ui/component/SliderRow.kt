package com.example.onlineshop.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.onlineshop.model.site.Slider
import com.example.onlineshop.viewmodel.HomeViewModel

@Composable
fun SliderRow(homeViewModel: HomeViewModel) {
    when {
        homeViewModel.sliders.isLoading -> {
            Loading(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
        }

        homeViewModel.sliders.error != null -> {
            ErrorBox(
                homeViewModel.sliders.error ?: "Error",
                Modifier.height(200.dp)
            )
        }

        homeViewModel.sliders.data != null -> {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                itemsIndexed(homeViewModel.sliders.data as List<Slider>) { index, slider ->
                    AnimatedSlideIn(index * 100) {
                        SliderItem(slider = slider)
                    }
                }
            }
        }
    }
}

@Composable
fun SliderItem(slider: Slider) {
    Card(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = true
            ),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AppImage(slider.image ?: "", slider.title ?: "")
            AppGradient(
                Modifier
                    .height(70.dp)
                    .align(Alignment.BottomCenter)
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = slider.title ?: "",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = slider.subtitle ?: "",
                    color = Color.White
                )
            }
        }
    }
}