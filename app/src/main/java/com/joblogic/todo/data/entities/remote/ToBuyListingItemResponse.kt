package com.joblogic.todo.data.entities.remote

import com.google.gson.annotations.SerializedName

data class ToBuyListingItemResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("price")
    val price: Double? = null,
    @SerializedName("quantity")
    val quantity: Int? = null,
    @SerializedName("type")
    val type: Int? = null
)
