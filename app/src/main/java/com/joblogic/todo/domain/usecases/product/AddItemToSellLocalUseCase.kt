package com.joblogic.todo.domain.usecases.product

import com.joblogic.todo.data.repositories.ProductRepository
import com.joblogic.todo.domain.entities.product.ToBuyItem
import javax.inject.Inject

class AddItemToSellLocalUseCase @Inject constructor(
    private val repository: ProductRepository
) {
    fun invoke(input: List<ToBuyItem>) {
        repository.addItemToSellToLocal(input)
    }
}