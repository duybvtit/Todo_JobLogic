package com.joblogic.todo.domain.api

import com.joblogic.todo.data.entities.remote.ToBuyListingItemResponse
import com.joblogic.todo.data.entities.remote.ToCallListingItemResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface ProductAPI {
    companion object {
        const val GET_CALL_LISTING = "demo-1/call"
        const val GET_BUY_LISTING = "demo-1/buy"
    }

    @GET(GET_CALL_LISTING)
    fun getCallListing(): Flow<List<ToCallListingItemResponse>>

    @GET(GET_BUY_LISTING)
    fun getBuyListing(): Flow<List<ToBuyListingItemResponse>>
}