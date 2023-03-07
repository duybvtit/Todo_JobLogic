package com.joblogic.todo.data.mappers

import com.joblogic.todo.data.entities.local.ItemToSellLocal
import com.joblogic.todo.data.entities.remote.ToBuyListingItemResponse
import com.joblogic.todo.data.entities.remote.ToCallListingItemResponse
import com.joblogic.todo.domain.entities.product.ToBuyItem
import com.joblogic.todo.domain.entities.product.ToCallItem


fun ToBuyListingItemResponse.toBuyListingItem(): ToBuyItem {
    return ToBuyItem(
        id = this.id ?: 0,
        name = this.name ?: "",
        price = this.price ?: 0.0,
        quantity = this.quantity ?: 0,
        type = this.type ?: 0
    )
}

fun ToCallListingItemResponse.toCallListingItem(): ToCallItem {
    return ToCallItem(
        id = this.id ?: 0,
        name = this.name ?: "",
        number = this.number ?: ""
    )
}

fun ToBuyItem.toItemToSellLocal(): ItemToSellLocal {
    return ItemToSellLocal(
        id = this.id,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        type = this.type
    )
}

fun ItemToSellLocal.toBuyItem(): ToBuyItem {
    return ToBuyItem(
        id = this.id,
        name = this.name,
        price = this.price,
        quantity = this.quantity,
        type = this.type
    )
}