package com.example.androidtestapplication.presentation.ui.menu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtestapplication.R
import com.example.androidtestapplication.databinding.ItemOfRecyclerViewCategoryBinding
import com.example.androidtestapplication.util.TextFormator

class CategoryAdapter : RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {

    private var listOfCategory: List<String> = listOf()
    private var currentSelectedItemIndex: Int = 0
    private var onItemSelected: ((String) -> Unit)? = null

    inner class ViewHolder(private val binding: ItemOfRecyclerViewCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(categoryName: String) {
            with(binding) {
                categryName.text = TextFormator().firstLetterCapitalAndRestAreSmall(categoryName)
                changeСolorOfCardAndTextWhenClickCategory()

                root.setOnClickListener {
                    onItemSelected?.invoke(categoryName)

                    notifyItemChanged(currentSelectedItemIndex)
                    currentSelectedItemIndex = adapterPosition
                    notifyItemChanged(currentSelectedItemIndex)
                }
            }
        }

        private fun ItemOfRecyclerViewCategoryBinding.changeСolorOfCardAndTextWhenClickCategory() {
            if (currentSelectedItemIndex == adapterPosition) {
                categoryCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                        categoryCard.context,
                        R.color.light_red_color
                    )
                )
                categryName.setTextColor(
                    ContextCompat.getColor(
                        categoryCard.context,
                        R.color.red_color
                    )
                )
            } else {
                categoryCard.setCardBackgroundColor(
                    ContextCompat.getColor(
                        categoryCard.context,
                        R.color.white
                    )
                )
                categryName.setTextColor(
                    ContextCompat.getColor(
                        categoryCard.context,
                        R.color.text_color_gray_secondary
                    )
                )
            }
        }
    }

    fun setOnItemClickListener(block: ((String) -> Unit)) {
        onItemSelected = block
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(listOfCategory: List<String>) {
        this.listOfCategory = listOfCategory
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemOfRecyclerViewCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listOfCategory.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(listOfCategory[position])
    }
}