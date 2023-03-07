package com.joblogic.todo.repositories

import com.joblogic.todo.AndroidTest
import com.joblogic.todo.data.database.ProductDao
import com.joblogic.todo.data.entities.local.ItemToSellLocal
import com.joblogic.todo.data.repositories.ProductRepository
import com.joblogic.todo.data.services.ProductServices
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.entities.product.ToBuyItem
import com.joblogic.todo.domain.entities.product.ToCallItem
import com.joblogic.todo.util.createBuyListingResponse
import com.joblogic.todo.util.createCallListingResponse
import io.mockk.every
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.Before
import org.junit.Test

class ProductRepositoryTest : AndroidTest() {
    @MockK
    lateinit var productServices: ProductServices

    @MockK
    lateinit var productDao: ProductDao

    private lateinit var productRepository: ProductRepository

    @Before
    fun setUp() {
        productRepository = ProductRepository(service = productServices, productDao = productDao)
    }

    @Test
    fun `test get API buy list successfully`() = runBlocking {
        //given

        every {
            productServices.getBuyListing()
        } returns flowOf(createBuyListingResponse())


        productRepository.getBuyListing().collect { result ->
            //verify app result type
            result shouldBeInstanceOf AppResult.Success::class.java

            //verify size of response, should be not empty
            (result as AppResult.Success<List<ToBuyItem>>).data?.size shouldBeEqualTo 3

            //verify id of response, should be not empty
            result.data?.get(0)?.id shouldBeEqualTo 0
        }
    }

    @Test
    fun `test get API buy list fail`() = runBlocking {
        //given

        every {
            productServices.getBuyListing()
        } returns flowOf(createBuyListingResponse())


        productRepository.getBuyListing().collect { result ->
            //verify app result type
            result shouldBeInstanceOf AppResult.Success::class.java

            //verify size of response, should be not empty
            (result as AppResult.Success<List<ToBuyItem>>).data?.size shouldBeEqualTo 4

            //verify id of response, should be not empty
            result.data?.get(0)?.id shouldBeEqualTo 0
        }
    }

    @Test
    fun `test get API call list successfully`() = runBlocking {
        //given

        every {
            productServices.getCallListing()
        } returns flowOf(createCallListingResponse())


        productRepository.getCallListing().collect { result ->
            //verify app result type
            result shouldBeInstanceOf AppResult.Success::class.java

            //verify size of response, should be not empty
            (result as AppResult.Success<List<ToCallItem>>).data?.size shouldBeEqualTo 4

            //verify id of response, should be not empty
            result.data?.get(0)?.id shouldBeEqualTo 0
        }
    }

    @Test
    fun `test get API call list fail`() = runBlocking {
        //given

        every {
            productServices.getCallListing()
        } returns flowOf(createCallListingResponse())


        productRepository.getCallListing().collect { result ->
            //verify app result type
            result shouldBeInstanceOf AppResult.Success::class.java

            //verify size of response, should be not empty
            (result as AppResult.Success<List<ToCallItem>>).data?.size shouldBeEqualTo 5

            //verify id of response, should be not empty
            result.data?.get(0)?.id shouldBeEqualTo 0
        }
    }

    @Test
    fun `get local product list successfully`() = runBlocking {
        every {
            productDao.getAllItemToSell()
        } returns listOf(ItemToSellLocal(), ItemToSellLocal())

        productRepository.getAllItemToSellLocal().collect { result ->
            result.size shouldBeEqualTo 2
        }

    }

}