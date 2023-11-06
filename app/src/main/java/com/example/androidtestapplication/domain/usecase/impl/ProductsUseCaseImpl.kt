package com.example.androidtestapplication.domain.usecase.impl

import com.example.androidtestapplication.domain.repository.ProductsRepository
import com.example.androidtestapplication.domain.usecase.ProductsUseCase
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow

class ProductsUseCaseImpl(private val productsRepository: ProductsRepository) : ProductsUseCase {
    override suspend fun execute(categoryName: String) = flow {
        productsRepository.getProductsByCategoryName(categoryName).collect {
            emit(it)
        }
    }
}