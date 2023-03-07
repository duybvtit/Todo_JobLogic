package com.joblogic.todo.dao

import androidx.room.Room
import com.joblogic.todo.AndroidTest
import com.joblogic.todo.data.database.ProductDao
import com.joblogic.todo.data.database.TodoDatabase
import com.joblogic.todo.data.entities.local.ItemToSellLocal
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ProductDaoTest : AndroidTest() {

    private lateinit var todoDatabase: TodoDatabase

    private lateinit var productDao: ProductDao

    @Before
    fun setUp() {
        todoDatabase = Room.inMemoryDatabaseBuilder(context(), TodoDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        productDao = todoDatabase.productDao()
    }

    @After
    fun tearDown() {
        todoDatabase.close()
    }

    @Test
    fun `add product list and get list from local`() {
        //given
        val products = listOf(
            ItemToSellLocal(id = 0, name = "Macbook", price = 0.0, quantity = 0, type = 0),
            ItemToSellLocal(id = 1, name = "Macbook 1", price = 0.0, quantity = 0, type = 0),
            ItemToSellLocal(id = 2, name = "Macbook 2", price = 0.0, quantity = 0, type = 0)
        )

        //when
        productDao.addListItem(products)

        //then
        val productList = productDao.getAllItemToSell()

        assertEquals(productList.size, products.size)
    }
}