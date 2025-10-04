package com.example.onlineshop.ui.component.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.onlineshop.model.site.Slider
import com.example.onlineshop.ui.component.animation.AnimatedSlideIn
import com.example.onlineshop.ui.component.basic.DataUiStateHandler
import com.example.onlineshop.viewmodel.HomeViewModel

@Composable
fun SliderRow(homeViewModel: HomeViewModel) {
    DataUiStateHandler(
        state = homeViewModel.sliders,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
    ) { sliders ->
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            itemsIndexed(sliders) { index, item ->
                AnimatedSlideIn(index * 100) {
                    SliderItem(slider = item)
                }
            }
        }
    }
}

@Composable
fun SliderItem(slider: Slider) {
    AppCard(
        modifier = Modifier
            .width(300.dp)
            .height(200.dp),
        image = slider.image,
        title = slider.title,
        subtitle = slider.subtitle,
    )
}