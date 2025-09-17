package com.example.onlineshop.model

data class ApiResponse<T>(
    val data: List<T>,
    val message: String,
    val status: String,
    val totalCount: Int
)
