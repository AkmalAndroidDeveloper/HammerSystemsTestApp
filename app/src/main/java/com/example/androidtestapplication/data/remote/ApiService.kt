package com.example.androidtestapplication.data.remote

import com.example.androidtestapplication.domain.model.ProductData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("products/categories")
    suspend fun getCategories(): Response<List<String>>

    @GET("products/category/{categoryName}")
    suspend fun getProductsByCategoryName(
        @Path("categoryName") categoryName: String
    ): Response<List<ProductData>>
}