package com.joblogic.todo.features.view.sell

import com.joblogic.todo.data.entities.local.ItemToSellLocal

data class ToSellDataState(
    val items: List<ItemToSellLocal> = emptyList()
)
