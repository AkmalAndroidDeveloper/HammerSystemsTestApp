package com.example.androidtestapplication.di

import com.example.androidtestapplication.data.repository.CategoriesRepositoryImpl
import com.example.androidtestapplication.data.repository.ProductsRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single {
        CategoriesRepositoryImpl(apiService = get())
    }
    single {
        ProductsRepositoryImpl(apiService = get())
    }
}