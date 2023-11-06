package com.example.androidtestapplication.domain.usecase.impl

import com.example.androidtestapplication.domain.repository.CategoriesRepository
import com.example.androidtestapplication.domain.usecase.CategoriesUseCase
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class CategoriesUseCaseImpl(private val categoriesRepository: CategoriesRepository) :
    CategoriesUseCase {
    override suspend fun execute() = flow {
        categoriesRepository.getCategories().collect {
            emit(it)
        }
    }
}