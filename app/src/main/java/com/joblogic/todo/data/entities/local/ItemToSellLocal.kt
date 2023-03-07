package com.joblogic.todo.data.entities.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ItemToSell")
data class ItemToSellLocal(
    @PrimaryKey
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "price")
    val price: Double = 0.0,
    @ColumnInfo(name = "quantity")
    val quantity: Int = 0,
    @ColumnInfo(name = "type")
    val type: Int = 0
)