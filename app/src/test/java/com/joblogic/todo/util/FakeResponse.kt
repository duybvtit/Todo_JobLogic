package com.joblogic.todo.util

import com.joblogic.todo.data.entities.remote.ToBuyListingItemResponse
import com.joblogic.todo.data.entities.remote.ToCallListingItemResponse


fun createCallListingResponse(): List<ToCallListingItemResponse> {
    return listOf(
        ToCallListingItemResponse(id = 0, name = "Test 1", number = "0123"),
        ToCallListingItemResponse(id = 1, name = "Test 2", number = "01234"),
        ToCallListingItemResponse(id = 2, name = "Test 3", number = "01235"),
        ToCallListingItemResponse(id = 3, name = "Test 4", number = "01236"),
    )
}

fun createBuyListingResponse(): List<ToBuyListingItemResponse> {
    return listOf(
        ToBuyListingItemResponse(id = 0, name = "Mac", price = 500.0, quantity = 1, type = 1),
        ToBuyListingItemResponse(id = 1, name = "Mac 1", price = 500.0, quantity = 1, type = 1),
        ToBuyListingItemResponse(id = 2, name = "Mac 2", price = 500.0, quantity = 1, type = 1),
    )
}