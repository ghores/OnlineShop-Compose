package com.example.onlineshop.ui.component.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun AppImage(
    model: String,
    description: String,
    contentScale: ContentScale = ContentScale.Crop
) {
    var loading by remember { mutableStateOf(false) }
    AsyncImage(
        model = model,
        contentDescription = description,
        modifier = Modifier.fillMaxSize(),
        contentScale = contentScale,
        onLoading = {
            loading = true
        },
        onError = {
            loading = false
        },
        onSuccess = {
            loading = false
        }
    )
    if (loading) {
        Loading()
    }
}