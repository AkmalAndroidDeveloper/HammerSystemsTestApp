package com.example.androidtestapplication.presentation.ui.menu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidtestapplication.databinding.ItemOfPagerBinding

class PagerAdapter : RecyclerView.Adapter<PagerAdapter.ViewHolder>() {

    private var listOfPagerImages: List<String> = listOf()

    inner class ViewHolder(private val binding: ItemOfPagerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(image: String) {
            with(binding) {
                Glide
                    .with(pagerImage.context)
                    .load(image)
                    .centerCrop()
                    .into(pagerImage)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(listOfPagerImages: List<String>) {
        this.listOfPagerImages = listOfPagerImages
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemOfPagerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listOfPagerImages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listOfPagerImages[position])
    }
}