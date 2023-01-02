package com.example.buywzme.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.buyweme.databinding.FragmentProductDetailsBinding
import com.example.buywzme.adapters.ColorAdapter
import com.example.buywzme.adapters.SizeAdapter
import com.example.buywzme.adapters.viewPager2Images

class ProductDetailsFragment : Fragment() {
    private val args by navArgs<ProductDetailsFragmentArgs>()
    private lateinit var binding: FragmentProductDetailsBinding
    private val viewPagerAdapter by lazy { viewPager2Images() }
    private val sizesAdapter by lazy { SizeAdapter() }
    private val colorsAdapter by lazy { ColorAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductDetailsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val product = args.product

        setupSizesRv()
        setupColorsRv()
        setupViewPager()


        binding.apply {
            tvProductName.text = product.name
            tvProductPrice.text = "$ ${product.price}"
            tvProductDesc.text = product.description
        }
        viewPagerAdapter.differ.submitList(product.images)
        product.colors?.let {
            colorsAdapter.differ.submitList(it)
        }
        product.sizes?.let {
            sizesAdapter.differ.submitList(it)
        }
    }

    private fun setupViewPager() {
        binding.apply {
            viewPagerImages.adapter = viewPagerAdapter
        }
    }

    private fun setupColorsRv() {
        binding.rvColors.apply {
            adapter = colorsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setupSizesRv() {
        binding.rvSizes.apply {
            adapter = sizesAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}