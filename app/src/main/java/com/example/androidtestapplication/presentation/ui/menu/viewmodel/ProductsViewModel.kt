package com.example.androidtestapplication.presentation.ui.menu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestapplication.data.repository.ProductsRepositoryImpl
import com.example.androidtestapplication.domain.model.ProductData
import com.example.androidtestapplication.domain.usecase.impl.ProductsUseCaseImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productsRepositoryImpl: ProductsRepositoryImpl
) : ViewModel() {

    private val _successFlow = MutableSharedFlow<List<ProductData>>()
    val successFlow = _successFlow

    private val _messageFlow = MutableSharedFlow<String>()
    val messageFlow = _messageFlow

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow

    fun getProductsByCategoryName(categoryName: String) {
        viewModelScope.launch {
            ProductsUseCaseImpl(productsRepositoryImpl).execute(categoryName)
                .catch {
                    _errorFlow.emit(it.localizedMessage ?: "Ошибка")
                }
                .collectLatest {
                    if (it.isSuccessful) {
                        _successFlow.emit(it.body() ?: listOf())
                    } else {
                        _messageFlow.emit(it.message())
                    }
                }
        }
    }

    fun insert(categoryName: String) {

    }
}