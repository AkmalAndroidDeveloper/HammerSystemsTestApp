package com.example.androidtestapplication.data.repository

import com.example.androidtestapplication.data.remote.ApiService
import com.example.androidtestapplication.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.flow

class ProductsRepositoryImpl(private val apiService: ApiService) : ProductsRepository {

    override suspend fun getProductsByCategoryName(categoryName: String) = flow {
        val response = apiService.getProductsByCategoryName(categoryName)
        emit(response)
    }
}