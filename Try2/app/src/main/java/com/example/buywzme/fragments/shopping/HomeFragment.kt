package com.example.buywzme.fragments.shopping

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.buyweme.R
import com.example.buyweme.databinding.FragmentHomeBinding
import com.example.buywzme.adapters.HomeViewPagerAdapter
import com.example.buywzme.fragments.categories.*
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoriesFragment = arrayListOf<Fragment>(
            MainCategoryFragment(),
            ChairFragment(),
            CupboardFragment(),
            TableFragment(),
            AccessoriesFragment(),
            FurnitureFragment()
        )
        binding.viewPagerHome.isUserInputEnabled = false


        val viewPager2Adapter =
            HomeViewPagerAdapter(categoriesFragment, childFragmentManager, lifecycle)
        binding.viewPagerHome.adapter = viewPager2Adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPagerHome){ tab, position ->
            when(position){
                0 -> tab.text= "Main"
                1 -> tab.text= "Chair"
                2 -> tab.text= "Cupboard"
                3 -> tab.text= "Table"
                4 -> tab.text= "Accessories"
                5 -> tab.text= "Furniture"


            }

        }.attach()

    }

}