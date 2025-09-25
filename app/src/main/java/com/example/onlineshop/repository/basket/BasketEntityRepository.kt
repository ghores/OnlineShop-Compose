package com.example.onlineshop.repository.basket

import com.example.onlineshop.dao.BasketEntityDao
import com.example.onlineshop.model.db.BasketEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BasketEntityRepository @Inject constructor(
    private val basketEntityDao: BasketEntityDao
) {
    fun findBasketItem(productId: Long, colorId: Long, sizeId: Long): BasketEntity? {
        return basketEntityDao.findBasketItem(productId, colorId, sizeId)
    }

    fun incrementQuantity(item: BasketEntity) {
        basketEntityDao.incrementQuantity(item.id)
    }

    fun decrementQuantity(item: BasketEntity) {
        if (item.quantity == 1) {
            return
        }
        basketEntityDao.decrementQuantity(item.id)
    }

    fun deleteBasketItem(item: BasketEntity) {
        basketEntityDao.deleteBasketItem(item)
    }

    fun addToBasket(item: BasketEntity) {
        basketEntityDao.addToBasket(item)
    }

    fun getAllBasketItems(): Flow<List<BasketEntity>> {
        return basketEntityDao.getAllBasketItems()
    }
}