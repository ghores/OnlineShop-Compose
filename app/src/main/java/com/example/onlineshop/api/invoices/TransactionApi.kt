package com.example.onlineshop.api.invoices

import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.invoices.PaymentTransaction
import retrofit2.http.Body
import retrofit2.http.POST

interface TransactionApi {
    @POST("trx/gotoPayment")
    suspend fun goToPayment(
        @Body data: PaymentTransaction
    ): ApiResponse<String>
}