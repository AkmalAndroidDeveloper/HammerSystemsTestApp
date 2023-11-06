package com.example.androidtestapplication.presentation.ui.menu

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtestapplication.R
import com.example.androidtestapplication.databinding.FragmentMenuBinding
import com.example.androidtestapplication.presentation.ui.menu.adapter.CategoryAdapter
import com.example.androidtestapplication.presentation.ui.menu.adapter.PagerAdapter
import com.example.androidtestapplication.presentation.ui.menu.adapter.ProductAdapter
import com.example.androidtestapplication.presentation.ui.menu.viewmodel.CategoriesViewModel
import com.example.androidtestapplication.presentation.ui.menu.viewmodel.ProductsViewModel
import com.example.androidtestapplication.util.PagerImagesProvider
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment(R.layout.fragment_menu) {

    private var _binding: FragmentMenuBinding? = null
    private var categoryAdapter: CategoryAdapter? = null
    private var productAdapter: ProductAdapter? = null
    private var pagerAdapter: PagerAdapter? = null
    private var defaultCategoryName: String? = null
    private var pagerHandler: Handler? = null
    private var pagerRunnable: Runnable? = null

    private val binding get() = _binding!!
    private val categoriesViewModel: CategoriesViewModel by viewModel()
    private val productsViewModel: ProductsViewModel by viewModel()

    companion object {
        const val TAG = "MenuFragment"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindView(view)

        initValues()
        getDataFromNetwork()
        automaticallyPagerSlide()
        initObservables()
        initListeners()
    }

    private fun automaticallyPagerSlide() {
        var nextPagerItemIndex = 0
        var pagerImagesCount = PagerImagesProvider().getPagerImages().size
        val pagerHandlerDelayMillis = 2000L

        pagerRunnable = object : Runnable {
            override fun run() {
                nextPagerItemIndex++

                if (nextPagerItemIndex == pagerImagesCount)
                    nextPagerItemIndex = 0

                binding.viewPager.currentItem = nextPagerItemIndex
                pagerHandler?.postDelayed(this, pagerHandlerDelayMillis)
            }
        }
        pagerHandler = Handler(Looper.getMainLooper())
        pagerHandler?.postDelayed(
            pagerRunnable as Runnable,
            pagerHandlerDelayMillis
        )

    }

    private fun initListeners() {
        with(binding) {
            categoryAdapter?.setOnItemClickListener { categoryName ->
                recyclerViewProducts.visibility = View.GONE
                shimmerProductContainer.visibility = View.VISIBLE
                shimmerProductContainer.startShimmer()

                lifecycleScope.launch {
                    productsViewModel.getProductsByCategoryName(categoryName)
                }
            }
        }
    }

    private fun getDataFromNetwork() {
        categoriesViewModel.getCategories()
        productsViewModel.getProductsByCategoryName(defaultCategoryName ?: "")
    }

    private fun initValues() {
        defaultCategoryName = "electronics"

        initAdapters()
        initRecyclerViewsLayoutManagers()
        initRecyclerViewsAdapters()
        initViewPagerAdapterList()
        initViewPagerAdapter()
    }

    private fun initViewPagerAdapterList() {
        pagerAdapter?.submitList(PagerImagesProvider().getPagerImages())
    }

    private fun initViewPagerAdapter() {
        binding.viewPager.adapter = pagerAdapter
    }

    private fun initRecyclerViewsAdapters() {
        with(binding) {
            recyclerViewCategories.adapter = categoryAdapter
            recyclerViewProducts.adapter = productAdapter
        }
    }

    private fun initRecyclerViewsLayoutManagers() {
        with(binding) {
            val categoryReyclerViewLayoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.HORIZONTAL, false
            )
            val productReyclerViewLayoutManager = LinearLayoutManager(
                requireContext(), LinearLayoutManager.VERTICAL, false
            )

            recyclerViewCategories.layoutManager = categoryReyclerViewLayoutManager
            recyclerViewProducts.layoutManager = productReyclerViewLayoutManager
        }
    }

    private fun initAdapters() {
        categoryAdapter = CategoryAdapter()
        productAdapter = ProductAdapter()
        pagerAdapter = PagerAdapter()
    }

    private fun initObservables() {
        with(categoriesViewModel) {
            successFlow.onEach {
                with(binding) {
                    categoryAdapter?.submitList(it)
                    shimmerCategryContainer.stopShimmer()
                    shimmerCategryContainer.visibility = View.GONE
                    recyclerViewCategories.visibility = View.VISIBLE
                }
                Log.d(TAG, "Category SuccessFlow: $it")
            }.launchIn(lifecycleScope)
            messageFlow.onEach {
                Log.d(TAG, "Category MessageFlow: $it")
            }.launchIn(lifecycleScope)
            errorFlow.onEach {
                Log.d(TAG, "Category ErrorFlow: $it")
            }.launchIn(lifecycleScope)
        }
        with(productsViewModel) {
            successFlow.onEach {
                with(binding) {
                    productAdapter?.submitList(it)
                    shimmerProductContainer.stopShimmer()
                    shimmerProductContainer.visibility = View.GONE
                    recyclerViewProducts.visibility = View.VISIBLE
                }
                Log.d(TAG, "Product SuccessFlow: $it")
            }.launchIn(lifecycleScope)
            messageFlow.onEach {
                Log.d(TAG, "Product MessageFlow: $it")
            }.launchIn(lifecycleScope)
            errorFlow.onEach {
                Log.d(TAG, "Product ErrorFlow: $it")
            }.launchIn(lifecycleScope)
        }
    }

    private fun bindView(view: View) {
        _binding = FragmentMenuBinding.bind(view)
    }

    private fun unBindView() {
        _binding = null
    }

    override fun onDestroyView() {
        super.onDestroyView()
        pagerRunnable?.let { pagerHandler?.removeCallbacks(it) }
        unBindView()
    }
}