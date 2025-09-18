package com.example.onlineshop.model.invoices

import com.example.onlineshop.model.products.Product

data class InvoiceItem(
    var id: Long?,
    var product: Product? = null,
    var quantity: Int?,
    var unitPrice: Long?
)