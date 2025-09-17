package com.example.onlineshop.api.products

import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.products.ProductCategory
import retrofit2.http.GET

interface ProductCategoryApi {
    @GET("productCategory")
    suspend fun getCategories(): ApiResponse<ProductCategory>
}