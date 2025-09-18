package com.example.onlineshop.repository.invoices

import com.example.onlineshop.api.invoices.TransactionApi
import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.invoices.PaymentTransaction
import com.example.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val api: TransactionApi
) : BaseRepository() {
    suspend fun goToPayment(data: PaymentTransaction): ApiResponse<String> =
        safeApiCall {
            api.goToPayment(data)
        }
}