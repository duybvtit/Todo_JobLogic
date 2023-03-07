package com.joblogic.todo.features.view.sell.model

import com.joblogic.todo.data.entities.local.ItemToSellLocal

data class ToSellDataState(
    val items: List<ItemToSellLocal> = emptyList(),
    val isFetchingLocal: Boolean = false
)
