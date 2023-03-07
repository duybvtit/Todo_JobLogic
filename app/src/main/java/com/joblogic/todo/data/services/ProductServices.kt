package com.joblogic.todo.data.services

import com.joblogic.todo.data.entities.remote.ToBuyListingItemResponse
import com.joblogic.todo.data.entities.remote.ToCallListingItemResponse
import com.joblogic.todo.domain.api.ProductAPI
import kotlinx.coroutines.flow.Flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductServices @Inject constructor(retrofit: Retrofit) : ProductAPI {
    private val api by lazy {
        retrofit.newBuilder().build().create(ProductAPI::class.java)
    }

    override fun getCallListing(): Flow<List<ToCallListingItemResponse>> {
        return api.getCallListing()
    }

    override fun getBuyListing(): Flow<List<ToBuyListingItemResponse>> {
        return api.getBuyListing()
    }
}