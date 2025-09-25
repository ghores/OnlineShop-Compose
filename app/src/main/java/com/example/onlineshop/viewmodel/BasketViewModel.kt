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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketEntityRepository: BasketEntityRepository
) : ViewModel() {
    val basketItems = basketEntityRepository.getAllBasketItems().stateIn(
        viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

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

    fun increaseQuantity(basketEntity: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            basketEntityRepository.incrementQuantity(basketEntity)
        }
    }

    fun decreaseQuantity(basketEntity: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            basketEntityRepository.decrementQuantity(basketEntity)
        }
    }

    fun deleteItem(basketEntity: BasketEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            basketEntityRepository.deleteBasketItem(basketEntity)
        }
    }
}