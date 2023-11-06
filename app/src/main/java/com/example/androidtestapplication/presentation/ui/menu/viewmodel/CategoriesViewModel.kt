package com.example.androidtestapplication.presentation.ui.menu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidtestapplication.data.repository.CategoriesRepositoryImpl
import com.example.androidtestapplication.domain.usecase.impl.CategoriesUseCaseImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CategoriesViewModel(
    private val categoriesRepositoryImpl: CategoriesRepositoryImpl
) : ViewModel() {

    private val _successFlow = MutableSharedFlow<List<String>>()
    val successFlow = _successFlow

    private val _messageFlow = MutableSharedFlow<String>()
    val messageFlow = _messageFlow

    private val _errorFlow = MutableSharedFlow<String>()
    val errorFlow = _errorFlow

    fun getCategories() {
        viewModelScope.launch {
            CategoriesUseCaseImpl(categoriesRepositoryImpl).execute().catch {
                _errorFlow.emit(it.localizedMessage ?: "Ошибка")
            }.collect {
                if (it.isSuccessful) {
                    _successFlow.emit(it.body()!!)
                } else {
                    _messageFlow.emit(it.message())
                }
            }
        }
    }
}