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
class SingleProductViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : BaseViewModel() {
    private var productState by mutableStateOf(DataUiState<Product>())
    var product by mutableStateOf<Product?>(null)

    fun loadProduct(id: Long) {
        loadData(stateSetter = {
            productState = it
            product = productState.data?.firstOrNull()
        }) {
            productRepository.getProductById(id)
        }
    }
}