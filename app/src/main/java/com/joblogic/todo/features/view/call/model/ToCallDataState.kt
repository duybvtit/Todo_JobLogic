package com.joblogic.todo.features.view.call.model

import com.joblogic.todo.domain.entities.product.ToCallItem

data class ToCallDataState(
    val items: List<ToCallItem> = emptyList(),
    val error: String = ""
)
