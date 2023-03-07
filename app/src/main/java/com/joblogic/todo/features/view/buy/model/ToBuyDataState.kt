package com.joblogic.todo.features.view.buy.model

import com.joblogic.todo.domain.entities.product.ToBuyItem

data class ToBuyDataState(
    val items: List<ToBuyItem> = emptyList(),
    val error: String = ""
)
