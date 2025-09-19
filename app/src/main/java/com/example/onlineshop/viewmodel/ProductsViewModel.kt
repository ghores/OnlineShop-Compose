package com.example.onlineshop.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.onlineshop.model.products.Product
import com.example.onlineshop.repository.products.ProductRepository
import com.example.onlineshop.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {
    var products by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set

    private var pageIndex = 0
    private val pageSize = 5
    private var endReached = false

    fun loadProducts(categoryId: Long) {
        if (products.isLoading || endReached) return
        loadData({
            if (it.isLoading) {
                products = products.copy(isLoading = true)
            } else {
                if (it.data?.isEmpty() == true) {
                    endReached = true
                }
                pageIndex++
                val currentProducts = products.data?.toMutableList() ?: mutableListOf()
                currentProducts.addAll(it.data ?: listOf())
                products = products.copy(isLoading = false, data = currentProducts)
            }

        }) {
            productRepository.getProductByCategoryId(categoryId, pageIndex, pageSize)
        }
    }
}