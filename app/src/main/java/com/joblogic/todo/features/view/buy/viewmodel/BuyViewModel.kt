package com.joblogic.todo.features.view.buy.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.entities.product.ToBuyItem
import com.joblogic.todo.domain.usecases.product.AddItemToSellLocalUseCase
import com.joblogic.todo.domain.usecases.product.GetToBuyListingUseCase
import com.joblogic.todo.features.view.buy.model.ToBuyDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BuyViewModel @Inject constructor(
    private val getToBuyListingUseCase: GetToBuyListingUseCase,
    private val addItemToSellLocalUseCase: AddItemToSellLocalUseCase
) : ViewModel() {
    private val _toBuyDataState = MutableStateFlow(ToBuyDataState())
    val toBuyDataState = _toBuyDataState.asStateFlow()

    fun getBuyListing() {
        viewModelScope.launch {
            getToBuyListingUseCase.invoke(null).flowOn(Dispatchers.IO).collect {
                when (it) {
                    is AppResult.Success -> {
                        _toBuyDataState.update { state ->
                            state.copy(
                                items = it.data ?: emptyList()
                            )
                        }

                        //add data to local
                        addItemToSell(list = it.data ?: emptyList())
                    }

                    is AppResult.Failure -> {
                        _toBuyDataState.update { state ->
                            state.copy(
                                error = it.message
                            )
                        }
                    }
                }
            }
        }
    }

    private fun addItemToSell(list: List<ToBuyItem>) {
        viewModelScope.launch(Dispatchers.IO) {
            addItemToSellLocalUseCase.invoke(input = list)
        }
    }


}