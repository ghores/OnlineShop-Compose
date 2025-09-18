package com.example.onlineshop.repository.invoices

import com.example.onlineshop.api.invoices.InvoiceApi
import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.invoices.Invoice
import com.example.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class InvoiceRepository @Inject constructor(
    private val api: InvoiceApi
) : BaseRepository() {

    suspend fun getInvoiceById(id: Long, token: String): ApiResponse<Invoice> =
        safeApiCall {
            api.getInvoiceById(id, prepareToken(token))
        }

    suspend fun getInvoiceByUserId(
        userId: Long,
        pageIndex: Int,
        pageSize: Int,
        token: String
    ): ApiResponse<Invoice> =
        safeApiCall {
            api.getInvoicesByUserId(
                userId,
                pageIndex,
                pageSize,
                prepareToken(token)
            )
        }
}