package com.example.androidtestapplication.domain.usecase

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CategoriesUseCase {

    suspend fun execute(): Flow<Response<List<String>>>
}