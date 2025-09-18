package com.example.onlineshop.module

import com.example.onlineshop.api.customer.UserApi
import com.example.onlineshop.api.invoices.InvoiceApi
import com.example.onlineshop.api.invoices.TransactionApi
import com.example.onlineshop.api.products.ProductApi
import com.example.onlineshop.api.products.ProductCategoryApi
import com.example.onlineshop.api.site.SliderApi
import com.example.onlineshop.config.UnsafeSSLConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideApi(): Retrofit {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val client = UnsafeSSLConfig.unsafeOkHttpClient
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl("https://onlineshop.holosen.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideSliderApi(retrofit: Retrofit): SliderApi {
        return retrofit.create(SliderApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserApi(retrofit: Retrofit): UserApi {
        return retrofit.create(UserApi::class.java)
    }

    @Provides
    @Singleton
    fun provideInvoiceApi(retrofit: Retrofit): InvoiceApi {
        return retrofit.create(InvoiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransactionApi(retrofit: Retrofit): TransactionApi {
        return retrofit.create(TransactionApi::class.java)
    }

    @Provides
    @Singleton
    fun provideProductApi(retrofit: Retrofit): ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCategoryApi(retrofit: Retrofit): ProductCategoryApi {
        return retrofit.create(ProductCategoryApi::class.java)
    }
}