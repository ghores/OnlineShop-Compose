package com.example.onlineshop.api.site

import com.example.onlineshop.model.ApiResponse
import com.example.onlineshop.model.site.Slider
import retrofit2.http.GET

interface SliderApi {
    @GET("slider")
    suspend fun getSlider(): ApiResponse<Slider>
}