package com.example.androidtestapplication.domain.repository

import com.example.androidtestapplication.domain.model.ProductData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductsRepository {

    suspend fun getProductsByCategoryName(categoryName: String): Flow<Response<List<ProductData>>>
}