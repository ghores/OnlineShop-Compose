package com.example.onlineshop.repository.basket

import com.example.onlineshop.dao.BasketEntityDao
import com.example.onlineshop.model.db.BasketEntity
import javax.inject.Inject

class BasketEntityRepository @Inject constructor(
    private val basketEntityDao: BasketEntityDao
) {
    suspend fun findBasketItem(productId: Long, colorId: Long, sizeId: Long): BasketEntity? {
        return basketEntityDao.findBasketItem(productId, colorId, sizeId)
    }

    suspend fun incrementQuantity(item: BasketEntity) {
        basketEntityDao.incrementQuantity(item.id)
    }

    suspend fun addToBasket(item: BasketEntity) {
        basketEntityDao.addToBasket(item)
    }
}