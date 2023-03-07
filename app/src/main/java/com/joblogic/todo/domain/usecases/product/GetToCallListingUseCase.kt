package com.joblogic.todo.domain.usecases.product

import com.joblogic.todo.data.repositories.ProductRepository
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.entities.product.ToCallItem
import com.joblogic.todo.domain.usecases.IBaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToCallListingUseCase @Inject constructor(
    private val repository: ProductRepository
) : IBaseUseCase<String?, AppResult<List<ToCallItem>>> {

    override suspend fun invoke(input: String?): Flow<AppResult<List<ToCallItem>>> {
        return repository.getCallListing()
    }
}