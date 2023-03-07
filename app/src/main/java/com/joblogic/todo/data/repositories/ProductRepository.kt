package com.joblogic.todo.data.repositories

import com.google.gson.Gson
import com.joblogic.todo.data.database.ProductDao
import com.joblogic.todo.data.entities.local.ItemToSellLocal
import com.joblogic.todo.data.mappers.toBuyListingItem
import com.joblogic.todo.data.mappers.toCallListingItem
import com.joblogic.todo.data.mappers.toItemToSellLocal
import com.joblogic.todo.data.services.ProductServices
import com.joblogic.todo.domain.entities.AppResult
import com.joblogic.todo.domain.entities.FailEntity
import com.joblogic.todo.domain.entities.product.ToBuyItem
import com.joblogic.todo.domain.entities.product.ToCallItem
import com.joblogic.todo.domain.repositories.IProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

@OptIn(FlowPreview::class)
class ProductRepository @Inject constructor(
    private val service: ProductServices,
    private val productDao: ProductDao
) : IProductRepository {
    override fun getCallListing(): Flow<AppResult<List<ToCallItem>>> {
        return flow {
            service.getCallListing().flowOn(Dispatchers.IO).flatMapMerge {
                flow {
                    emit(it.map { it.toCallListingItem() })
                }
            }.catch {
                emit(captureError(it))
            }.collect { responseData ->
                emit(AppResult.Success(responseData))
            }
        }
    }

    override fun getBuyListing(): Flow<AppResult<List<ToBuyItem>>> {
        return flow {
            service.getBuyListing().flowOn(Dispatchers.IO).flatMapMerge {
                flow {
                    emit(it.map { it.toBuyListingItem() })
                }
            }.catch {
                emit(captureError(it))
            }.collect { responseData ->
                emit(AppResult.Success(responseData))
            }
        }
    }

    override fun addItemToSellToLocal(items: List<ToBuyItem>) {
        productDao.addListItem(items = items.map { it.toItemToSellLocal() })
    }

    override fun getAllItemToSellLocal(): Flow<List<ItemToSellLocal>> {
        return flow {
            val items = productDao.getAllItemToSell()
            emit(items)
        }
    }

    private fun captureError(throwable: Throwable): AppResult.Failure {
        try {
            var errorBody = ""
            var errorKey = ""
            var message = ""
            var errorCode = 400

            when (throwable) {
                is HttpException -> {
                    errorBody =
                        throwable.response()?.errorBody()?.string()
                            ?.replace("\"data\":\"\"", "\"data\":{}")
                            ?: ""
                    errorCode = throwable.code()

                    if (errorBody.isEmpty()) {
                        return AppResult.Failure(exception = java.lang.Exception(throwable.message))
                    }

                    //using gson to capture error key and message
                    Gson().fromJson(errorBody, FailEntity::class.java)?.let {
                        message = it.message
                        errorKey = it.errorKey
                    }
                }

                else -> {
                    message = throwable.message ?: ""
                }
            }

            return AppResult.Failure(
                exception = java.lang.Exception(throwable.message),
                errorCode = errorCode,
                message = message.ifEmpty { "" },
                errorKey = errorKey.ifEmpty { "" }
            )
        } catch (e: java.lang.Exception) {
            return AppResult.Failure(exception = java.lang.Exception(e.message))
        }
    }

}