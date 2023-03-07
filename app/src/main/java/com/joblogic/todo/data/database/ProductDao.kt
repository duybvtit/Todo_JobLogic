package com.joblogic.todo.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.joblogic.todo.data.entities.local.ItemToSellLocal

@Dao
interface ProductDao {
    @Query("Select * from ItemToSell")
    fun getAllItemToSell(): List<ItemToSellLocal>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addListItem(items: List<ItemToSellLocal>)
}