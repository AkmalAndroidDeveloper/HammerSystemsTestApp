package com.example.androidtestapplication.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("products_table")
data class ProductData (
    @PrimaryKey
    val id: Int,
    val title: String,
    val price: String,
    val category: String,
    val description: String,
    val image: String
)