package com.example.onlineshop.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.model.site.Slider
import com.example.onlineshop.repository.products.ProductCategoryRepository
import com.example.onlineshop.repository.products.ProductRepository
import com.example.onlineshop.repository.site.SliderRepository
import com.example.onlineshop.ui.state.DataUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sliderRepository: SliderRepository,
    private val productCategoryRepository: ProductCategoryRepository,
    private val productRepository: ProductRepository
) : ViewModel() {

    var sliders by mutableStateOf<DataUiState<Slider>>(DataUiState())
        private set

    init {
        loadSliders()
    }
    fun loadSliders() {
        sliders = DataUiState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = sliderRepository.getSlider()
                if (response.status != "OK") {
                    throw Exception(response.message)
                }
            } catch (e: Exception) {
                sliders = DataUiState(error = e.message)
            }
        }
    }
}