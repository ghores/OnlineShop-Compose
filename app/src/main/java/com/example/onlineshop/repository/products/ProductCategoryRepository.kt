package com.example.onlineshop.repository.products

import com.example.onlineshop.api.products.ProductCategoryApi
import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.products.ProductCategory
import com.example.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class ProductCategoryRepository @Inject constructor(
    private val api: ProductCategoryApi
) : BaseRepository() {
    suspend fun getCategories(): ApiResponse<ProductCategory> =
        safeApiCall {
            api.getCategories()
        }
}
