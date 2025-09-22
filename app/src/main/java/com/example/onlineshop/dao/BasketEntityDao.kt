package com.example.onlineshop.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.onlineshop.model.db.BasketEntity

@Dao
interface BasketEntityDao {
    @Query("SELECT * FROM BasketEntity WHERE productId = :productId AND colorId = :colorId AND sizeId = :sizeId LIMIT 1")
    fun findBasketItem(productId: Long, colorId: Long, sizeId: Long): BasketEntity?

    @Insert
    fun addToBasket(item: BasketEntity)

    @Query("UPDATE BasketEntity SET quantity = quantity + 1 WHERE id = :id")
    fun incrementQuantity(id: Int)
}