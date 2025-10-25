package com.example.laecards.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.laecards.data.local.dao.CardDao
import com.example.laecards.data.local.model.CardEntity

@Database(entities = [CardEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cardDao(): CardDao
}