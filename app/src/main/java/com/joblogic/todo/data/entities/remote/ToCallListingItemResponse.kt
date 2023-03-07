package com.joblogic.todo.data.entities.remote

import com.google.gson.annotations.SerializedName

data class ToCallListingItemResponse(
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("number")
    val number: String? = null
)