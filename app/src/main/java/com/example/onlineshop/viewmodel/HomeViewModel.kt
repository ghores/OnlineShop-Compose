package com.example.onlineshop.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.onlineshop.model.products.Product
import com.example.onlineshop.model.products.ProductCategory
import com.example.onlineshop.model.site.Slider
import com.example.onlineshop.repository.products.ProductCategoryRepository
import com.example.onlineshop.repository.products.ProductRepository
import com.example.onlineshop.repository.site.SliderRepository
import com.example.onlineshop.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sliderRepository: SliderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val productRepository: ProductRepository
) : BaseViewModel() {

    var sliders by mutableStateOf<DataUiState<Slider>>(DataUiState())
        private set

    var productCategories by mutableStateOf<DataUiState<ProductCategory>>(DataUiState())
        private set

    var products by mutableStateOf<DataUiState<Product>>(DataUiState())
        private set

    init {
        loadSliders()
        loadProductCategories()
        loadAllProducts()
    }

    private fun loadSliders() {
        loadData({ sliders = it }) {
            sliderRepository.getSliders()
        }
    }

    private fun loadProductCategories() {
        loadData({ productCategories = it }) {
            productCategoryRepository.getCategories()
        }
    }

    fun loadAllProducts() {
        loadData({ products = it }) {
            productRepository.getProduct(0, 6)
        }
    }

    fun loadNewProducts() {
        loadData({ products = it }) {
            productRepository.getNewProducts()
        }
    }

    fun loadPopularProducts() {
        loadData({ products = it }) {
            productRepository.getPopularProducts()
        }
    }
}