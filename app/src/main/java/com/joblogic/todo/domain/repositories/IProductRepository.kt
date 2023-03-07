package com.joblogic.todo.domain.repositories

import com.joblogic.todo.data.entities.local.ItemToSellLocal
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.entities.product.ToBuyItem
import com.joblogic.todo.domain.entities.product.ToCallItem
import kotlinx.coroutines.flow.Flow

interface IProductRepository {
    fun getCallListing(): Flow<AppResult<List<ToCallItem>>>

    fun getBuyListing(): Flow<AppResult<List<ToBuyItem>>>

    fun addItemToSellToLocal(items: List<ToBuyItem>)

    fun getAllItemToSellLocal(): Flow<List<ItemToSellLocal>>
}