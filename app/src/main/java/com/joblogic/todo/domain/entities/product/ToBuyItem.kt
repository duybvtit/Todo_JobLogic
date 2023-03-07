package com.joblogic.todo.domain.entities.product

data class ToBuyItem(
    val id: Int = 0,
    val name: String = "",
    val price: Double = 0.0,
    val quantity: Int = 0,
    val type: Int = 0
)
