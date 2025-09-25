package com.example.onlineshop.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.onlineshop.model.db.BasketEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketEntityDao {
    @Insert
    fun addToBasket(item: BasketEntity)

    @Delete
    fun deleteBasketItem(item: BasketEntity)

    @Query("SELECT * FROM BasketEntity WHERE productId = :productId AND colorId = :colorId AND sizeId = :sizeId LIMIT 1")
    fun findBasketItem(productId: Long, colorId: Long, sizeId: Long): BasketEntity?

    @Query("SELECT * FROM BasketEntity")
    fun getAllBasketItems(): Flow<List<BasketEntity>>

    @Query("UPDATE BasketEntity SET quantity = quantity + 1 WHERE id = :id")
    fun incrementQuantity(id: Int)

    @Query("UPDATE BasketEntity SET quantity = quantity - 1 WHERE id = :id")
    fun decrementQuantity(id: Int)

}