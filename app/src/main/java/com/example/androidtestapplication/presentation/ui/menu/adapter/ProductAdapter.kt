package com.example.androidtestapplication.presentation.ui.menu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtestapplication.databinding.ItemOfRecyclerViewProductBinding
import com.example.androidtestapplication.domain.model.ProductData

class ProductAdapter : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var listOfProduct: List<ProductData> = listOf()

    inner class ViewHolder(private val binding: ItemOfRecyclerViewProductBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun onBind(productData: ProductData) {
            with(binding) {
                Glide.with(productImage.context).load(productData.image).centerCrop().into(productImage)
                productName.text = productData.title
                aboutProduct.text = productData.description
                productPrice.text = "от ${productData.price} р"
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(listOfProduct: List<ProductData>) {
        this.listOfProduct = listOfProduct
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemOfRecyclerViewProductBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listOfProduct.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listOfProduct[position])
    }
}