package com.joblogic.todo.viewmodel

import com.joblogic.todo.AndroidTest
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.entities.product.ToCallItem
import com.joblogic.todo.domain.usecases.product.GetToCallListingUseCase
import com.joblogic.todo.features.view.call.model.ToCallDataState
import com.joblogic.todo.features.view.call.viewmodel.CallViewModel
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

class CallViewModelTest : AndroidTest() {
    private lateinit var callViewModel: CallViewModel

    @MockK
    private lateinit var getToCallListingUseCase: GetToCallListingUseCase

    @Before
    fun setUp() {
        callViewModel = CallViewModel(getToCallListingUseCase)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun `test get call listing successfully`(): Unit = runBlocking {
        val responseList = listOf(ToCallItem(), ToCallItem())
        coEvery {
            getToCallListingUseCase.invoke(null)
        } answers {
            flowOf(AppResult.Success(responseList))
        }

        callViewModel.getCallListing()

        val result = arrayListOf<ToCallDataState>()
        val job = launch {
            callViewModel.toCallDataState.toList(result)
        }

        callViewModel.toCallDataState.value.items.size shouldBeEqualTo responseList.size
        callViewModel.toCallDataState.value.error shouldBeEqualTo ""
        job.cancel()

    }

    @Test
    fun `test get call listing fail`(): Unit = runBlocking {
        coEvery {
            getToCallListingUseCase.invoke(null)
        } answers {
            flowOf(AppResult.Failure(message = "fail", exception = Exception("message")))
        }

        callViewModel.getCallListing()

        val result = arrayListOf<ToCallDataState>()
        val job = launch {
            callViewModel.toCallDataState.toList(result)
        }

        //validate
        callViewModel.toCallDataState.value.error shouldBeEqualTo "fail"
        callViewModel.toCallDataState.value.items.size shouldBeEqualTo 0
        job.cancel()
    }
}