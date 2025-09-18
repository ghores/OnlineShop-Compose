package com.example.onlineshop.ui.state

data class DataUiState<T>(
    val isLoading: Boolean = false,
    val data: List<T> = emptyList(),
    val error: String? = null
)
