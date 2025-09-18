package com.example.onlineshop.repository.products

import com.example.onlineshop.api.products.ProductApi
import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.products.Product
import com.example.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class ProductRepository @Inject constructor(
    private val api: ProductApi
) : BaseRepository() {
    suspend fun getProduct(pageIndex: Int, pageSize: Int): ApiResponse<Product> =
        safeApiCall {
            api.getProduct(pageIndex, pageSize)
        }

    suspend fun getProductById(id: Long): ApiResponse<Product> =
        safeApiCall {
            api.getProductById(id)
        }

    suspend fun getProductByCategoryId(
        id: Long,
        pageIndex: Int,
        pageSize: Int
    ): ApiResponse<Product> =
        safeApiCall {
            api.getProductsByCategoryId(id, pageIndex, pageSize)
        }

    suspend fun getNewProducts(): ApiResponse<Product> =
        safeApiCall {
            api.getNewProducts()
        }

    suspend fun getPopularProducts(): ApiResponse<Product> =
        safeApiCall {
            api.getPopularProducts()
        }
}