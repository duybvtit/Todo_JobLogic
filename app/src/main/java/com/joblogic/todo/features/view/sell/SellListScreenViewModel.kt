package com.joblogic.todo.features.view.sell

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joblogic.todo.domain.usecases.product.GetToSellListingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SellListScreenViewModel @Inject constructor(
    private val getToSellListingUseCase: GetToSellListingUseCase
) : ViewModel() {

    private val _toSellDataState = MutableStateFlow(ToSellDataState())
    val toSellDataState = _toSellDataState.asStateFlow()


    fun getItemToSell() {
        viewModelScope.launch {
            getToSellListingUseCase.invoke(null).flowOn(Dispatchers.IO).collect {
                withContext(Dispatchers.Main) {
                    _toSellDataState.update { state ->
                        state.copy(
                            items = it
                        )
                    }
                }
            }
        }
    }

}