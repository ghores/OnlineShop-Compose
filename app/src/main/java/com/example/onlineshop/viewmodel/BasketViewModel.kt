package com.example.onlineshop.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.onlineshop.model.db.BasketEntity
import com.example.onlineshop.model.products.Product
import com.example.onlineshop.model.products.ProductColor
import com.example.onlineshop.model.products.ProductSize
import com.example.onlineshop.repository.basket.BasketEntityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketEntityRepository: BasketEntityRepository
) : ViewModel() {

    fun addToBasket(product: Product?, color: ProductColor?, size: ProductSize?) {
        viewModelScope.launch(Dispatchers.IO) {
            val oldItem = basketEntityRepository.findBasketItem(
                product?.id ?: 0,
                color?.id ?: 0,
                size?.id ?: 0
            )
            if (oldItem != null) {
                basketEntityRepository.incrementQuantity(oldItem)
            } else {
                basketEntityRepository.addToBasket(
                    BasketEntity(
                        productId = product?.id ?: 0,
                        colorId = color?.id ?: 0,
                        sizeId = size?.id ?: 0,
                        quantity = 1,
                        image = product?.image,
                        price = product?.price,
                        title = product?.title,
                        colorHex = color?.hexValue,
                        size = size?.title
                    )
                )
            }
        }
    }
}