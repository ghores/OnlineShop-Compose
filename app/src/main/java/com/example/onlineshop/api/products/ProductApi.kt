package com.example.onlineshop.api.products

import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.products.Product
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {
    @GET("product")
    suspend fun getProduct(
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ApiResponse<Product>

    @GET("product/{id}")
    suspend fun getProductById(
        @Path("id") id: Long
    ): ApiResponse<Product>

    @GET("product/cat/{id}")
    suspend fun getProductsByCategoryId(
        @Path("id") id: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int
    ): ApiResponse<Product>

    @GET("product/new")
    suspend fun getNewProducts(): ApiResponse<Product>

    @GET("product/popular")
    suspend fun getPopularProducts(): ApiResponse<Product>
}