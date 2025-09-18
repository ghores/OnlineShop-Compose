package com.example.onlineshop.model.invoices

import com.example.onlineshop.model.customer.User

data class Invoice(
    var id: Long?,
    var addDate: String?,
    var paymentDate: String?,
    var status: String?,
    var user: User? = null,
    var items: List<InvoiceItem>? = listOf()
)
