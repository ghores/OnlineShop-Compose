package com.example.onlineshop.model.invoices

import com.example.onlineshop.model.customer.UserDto

data class PaymentTransaction(
    var items: List<InvoiceItem>? = listOf(),
    var user: UserDto? = null
)
