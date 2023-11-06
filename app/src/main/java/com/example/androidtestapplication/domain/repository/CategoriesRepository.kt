package com.example.androidtestapplication.domain.repository

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CategoriesRepository {

    suspend fun getCategories(): Flow<Response<List<String>>>
}