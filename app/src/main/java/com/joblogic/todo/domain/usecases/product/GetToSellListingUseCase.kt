package com.joblogic.todo.domain.usecases.product

import com.joblogic.todo.data.entities.local.ItemToSellLocal
import com.joblogic.todo.data.repositories.ProductRepository
import com.joblogic.todo.domain.usecases.IBaseUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToSellListingUseCase @Inject constructor(
    private val repository: ProductRepository
) : IBaseUseCase<String?, List<ItemToSellLocal>> {

    override suspend fun invoke(input: String?): Flow<List<ItemToSellLocal>> {
        return repository.getAllItemToSellLocal()
    }


}