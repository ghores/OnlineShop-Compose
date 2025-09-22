package com.example.onlineshop.config

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.onlineshop.dao.BasketEntityDao
import com.example.onlineshop.model.db.BasketEntity

@Database(version = 1, entities = [BasketEntity::class])
abstract class AppDatabase : RoomDatabase() {
    abstract fun basketEntityDao(): BasketEntityDao

}