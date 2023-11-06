package com.example.androidtestapplication.presentation.ui.basket

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtestapplication.R
import com.example.androidtestapplication.databinding.FragmentBasketBinding

class BasketFragment : Fragment(R.layout.fragment_basket) {

    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
    }

    private fun unBindView() {
        _binding = null
    }

    private fun bindView(view: View) {
        _binding = FragmentBasketBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindView()
    }
}