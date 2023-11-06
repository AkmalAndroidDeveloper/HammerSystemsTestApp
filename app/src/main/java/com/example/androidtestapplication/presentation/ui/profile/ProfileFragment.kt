package com.example.androidtestapplication.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtestapplication.R
import com.example.androidtestapplication.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)
    }

    private fun bindView(view: View) {
        _binding = FragmentProfileBinding.bind(view)
    }

    private fun unBindView() {
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unBindView()
    }
}