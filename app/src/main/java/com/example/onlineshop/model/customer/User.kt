package com.example.onlineshop.model.customer

data class User(
    var id: Long?,
    var password: String?,
    var userName: String?,
    var customer: Customer? = null
)
