package com.joblogic.todo.viewmodel

import com.joblogic.todo.AndroidTest
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.entities.product.ToBuyItem
import com.joblogic.todo.domain.usecases.product.AddItemToSellLocalUseCase
import com.joblogic.todo.domain.usecases.product.GetToBuyListingUseCase
import com.joblogic.todo.features.view.buy.model.ToBuyDataState
import com.joblogic.todo.features.view.buy.viewmodel.BuyViewModel
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.amshove.kluent.shouldBeEqualTo
import org.junit.After
import org.junit.Before
import org.junit.Test

class BuyViewModelTest : AndroidTest() {
    private lateinit var buyViewModel: BuyViewModel

    @MockK
    private lateinit var getToBuyListingUseCase: GetToBuyListingUseCase

    @MockK
    private lateinit var addItemToSellLocalUseCase: AddItemToSellLocalUseCase

    @Before
    fun setUp() {
        buyViewModel = BuyViewModel(getToBuyListingUseCase, addItemToSellLocalUseCase)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `test get buy listing successfully`(): Unit = runBlocking {
        val responseList = listOf(ToBuyItem(), ToBuyItem())
        coEvery {
            getToBuyListingUseCase.invoke(null)
        } answers {
            flowOf(AppResult.Success(responseList))
        }

        buyViewModel.getBuyListing()

        val result = arrayListOf<ToBuyDataState>()
        val job = launch {
            buyViewModel.toBuyDataState.toList(result)
        }

        buyViewModel.toBuyDataState.value.items.size shouldBeEqualTo responseList.size
        buyViewModel.toBuyDataState.value.error shouldBeEqualTo ""
        job.cancel()

    }

    @Test
    fun `test get buy listing fail`(): Unit = runBlocking {
        coEvery {
            getToBuyListingUseCase.invoke(null)
        } answers {
            flowOf(AppResult.Failure(message = "fail", exception = Exception("message")))
        }

        buyViewModel.getBuyListing()

        val result = arrayListOf<ToBuyDataState>()
        val job = launch {
            buyViewModel.toBuyDataState.toList(result)
        }

        //validate
        buyViewModel.toBuyDataState.value.error shouldBeEqualTo "fail"
        buyViewModel.toBuyDataState.value.items.size shouldBeEqualTo 0
        job.cancel()
    }
}