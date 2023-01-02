package com.example.buywzme.fragments.categories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.buyweme.R
import com.example.buyweme.databinding.FragmentBaseBinding
import com.example.buywzme.adapters.BestProductAdapter


open class BaseCategoryFragment : Fragment(R.layout.fragment_base) {
    private lateinit var binding: FragmentBaseBinding
    protected  val offerAdapter: BestProductAdapter by lazy { BestProductAdapter() }
    protected val bestProductAdapter: BestProductAdapter by lazy { BestProductAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBaseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOfferRv()
        setupBestProductsRv()
        binding.rvOffer.addOnScrollListener(
            object : RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    if (!recyclerView.canScrollVertically(1) && dx !=0){
                        onOfferPagingRequest()
                    }
                }



            })
        binding.nestedScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener{v, _,scrollY,_,_ ->
            if (v.getChildAt(0).bottom <= v.height + scrollY){
                onBestPagingRequest()
            }

        })
    }



    open fun onOfferPagingRequest(){

    }
    open fun onBestPagingRequest(){

    }

    private fun setupBestProductsRv() {

        binding.productBest.apply {
            layoutManager =
                GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = bestProductAdapter
        }
    }

    private fun setupOfferRv() {

        binding.rvOffer.apply {
            layoutManager =
                LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
            adapter = offerAdapter
        }    }

}
