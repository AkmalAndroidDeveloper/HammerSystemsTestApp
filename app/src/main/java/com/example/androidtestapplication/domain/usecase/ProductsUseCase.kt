package com.example.androidtestapplication.domain.usecase

import com.example.androidtestapplication.domain.model.ProductData
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ProductsUseCase {

    suspend fun execute(categoryName: String): Flow<Response<List<ProductData>>>
}