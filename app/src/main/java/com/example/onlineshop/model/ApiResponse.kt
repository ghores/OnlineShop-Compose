package com.example.onlineshop.model

data class ApiResponse<T>(
    val data: List<T>? = listOf(),
    val message: String? = null,
    val status: String? = null,
    val totalCount: Int? = null
)
