package com.example.onlineshop.repository.site

import com.example.onlineshop.api.site.SliderApi
import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.site.Slider
import com.example.onlineshop.repository.base.BaseRepository
import javax.inject.Inject

class SliderRepository @Inject constructor(
    private val api: SliderApi
) : BaseRepository() {
    suspend fun getSlider(): ApiResponse<Slider> =
        safeApiCall {
            api.getSlider()
        }
}