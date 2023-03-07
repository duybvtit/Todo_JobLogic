package com.joblogic.todo.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.joblogic.todo.data.entities.local.ItemToSellLocal

@Database(
    entities = [
        ItemToSellLocal::class
    ],
    version = 31
)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    companion object {
        const val DATABASE_NAME = "TodoDatabase"
    }
}