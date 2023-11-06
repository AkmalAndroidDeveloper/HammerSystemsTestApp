package com.example.androidtestapplication.di

import com.example.androidtestapplication.domain.usecase.impl.CategoriesUseCaseImpl
import com.example.androidtestapplication.domain.usecase.impl.ProductsUseCaseImpl
import org.koin.dsl.module

val domainModule = module {

    factory {
        CategoriesUseCaseImpl(categoriesRepository = get())
    }
    factory {
        ProductsUseCaseImpl(productsRepository = get())
    }
}