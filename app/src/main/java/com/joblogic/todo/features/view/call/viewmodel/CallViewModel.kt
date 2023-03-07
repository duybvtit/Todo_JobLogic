package com.joblogic.todo.features.view.call.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.usecases.product.GetToCallListingUseCase
import com.joblogic.todo.features.view.buy.model.ToBuyDataState
import com.joblogic.todo.features.view.call.model.ToCallDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CallViewModel @Inject constructor(
    private val getToCallListingUseCase: GetToCallListingUseCase
) : ViewModel() {
    private val _toCallDataState = MutableStateFlow(ToCallDataState())
    val toCallDataState = _toCallDataState.asStateFlow()

    fun getCallListing() {
        viewModelScope.launch {
            getToCallListingUseCase.invoke(null).flowOn(Dispatchers.IO).collect {
                when (it) {
                    is AppResult.Success -> {
                        _toCallDataState.update { state ->
                            state.copy(
                                items = it.data ?: emptyList()
                            )
                        }
                    }

                    is AppResult.Failure -> {
                        _toCallDataState.update { state ->
                            state.copy(
                                error = it.message
                            )
                        }
                    }
                }
            }
        }
    }


}