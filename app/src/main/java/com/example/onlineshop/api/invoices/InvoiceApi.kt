package com.example.onlineshop.api.invoices

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface InvoiceApi {
    @GET("invoice/{id}")
    suspend fun getInvoiceById(
        @Path("id") id: Long,
        @Header("Authorization") token: String
    )

    @GET("invoice/user/{userId}")
    suspend fun getInvoicesByUserId(
        @Path("userId") userId: Long,
        @Query("pageIndex") pageIndex: Int,
        @Query("pageSize") pageSize: Int,
        @Header("Authorization") token: String
    )
}