package com.example.androidtestapplication.di

import com.example.androidtestapplication.presentation.ui.menu.viewmodel.CategoriesViewModel
import com.example.androidtestapplication.presentation.ui.menu.viewmodel.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        CategoriesViewModel(categoriesRepositoryImpl = get())
    }
    viewModel {
        ProductsViewModel(productsRepositoryImpl = get())
    }
}