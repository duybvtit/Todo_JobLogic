package com.joblogic.todo.domain.usecases

import kotlinx.coroutines.flow.Flow

interface IBaseUseCase<in I, out R> {
    suspend fun invoke(input: I): Flow<R>
}