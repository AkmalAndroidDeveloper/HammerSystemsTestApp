package com.example.androidtestapplication.data.repository

import com.example.androidtestapplication.data.remote.ApiService
import com.example.androidtestapplication.domain.repository.CategoriesRepository
import kotlinx.coroutines.flow.flow

class CategoriesRepositoryImpl(private val apiService: ApiService) : CategoriesRepository {

    override suspend fun getCategories() = flow {
        val response = apiService.getCategories()
        emit(response)
    }
}